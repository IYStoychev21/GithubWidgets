import express from 'express';
import session from 'express-session';
import passport from 'passport';
import { Strategy as GitHubStrategy } from 'passport-github2';
import cors from 'cors';
import axios from 'axios';

const app = express();

app.use(cors({
  origin: '*', 
  credentials: true,
}));

app.use(session({
  secret: 'your-secret-key',
  resave: false,
  saveUninitialized: true
}));

app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser((user, done) => {
  done(null, user);
});

passport.deserializeUser((obj, done) => {
  done(null, obj);
});

passport.use(new GitHubStrategy({
  clientID: process.env.GITHUB_CLIENT_ID || 'GITHUB_CLIENT_ID',
  clientSecret: process.env.GITHUB_CLIENT_SECRET || 'GITHUB_CLIENT_SECRET',
  callbackURL: process.env.GITHUB_CALLBACK_URL || 'http://localhost:3000/auth/github/callback'
},
(accessToken, refreshToken, profile, done) => {
  const user = { profile, accessToken, refreshToken };
  return done(null, user);
}));

app.get('/auth/github',
  passport.authenticate('github'));

app.get('/auth/github/callback',
  passport.authenticate('github', { failureRedirect: '/' }),
  (req, res) => {
    if (req.user) {
      // @ts-ignore
      res.redirect(`github-widgets://github-widgets/auth?token=${req.user.accessToken}`);
      // res.json(req.user);
    } else {
      res.redirect('/');
    }
  });

app.get('/logout', (req, res) => {
  req.logout((err) => {
    if (err) {
      return res.status(500).send('Error logging out');
    }
    res.redirect('/');
  });
});

app.get('/user', async (req, res) => {
  const token = req.headers.authorization

  if (!token) {
    return res.status(401).json({ message: 'Unauthorized' });
  }

  try {
    const user = await axios.get('https://api.github.com/user', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });

    const userProfile = user.data;

    const userResponse = {
      email: userProfile.email,
      name: userProfile.name,
      avatar: userProfile.avatar_url,
      username: userProfile.login,
      company: userProfile.company
    };

    res.send(userResponse);
  } catch (e) {
    res.status(401).json({ message: 'Unauthorized' });
  }
})

app.get("/get/contributions/:username", async (req, res) => {
  try {
    const contributions = await axios.get(`https://github-contributions-api.jogruber.de/v4/${req.params.username}?y=last`);
    res.send(contributions.data);
  } catch (e) {
    res.status(404).json({ message: 'User not found' });
  }
});

app.listen(process.env.PORT, () => {
  console.log('Server is running');
});
