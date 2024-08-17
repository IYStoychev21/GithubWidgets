import express from 'express';
import session from 'express-session';
import passport from 'passport';
import { Strategy as GitHubStrategy } from 'passport-github2';
import cors from 'cors';

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
  const user = { profile, accessToken };
  return done(null, user);
}));

app.get('/auth/github',
  passport.authenticate('github', { scope: ['user:username'] }));

app.get('/auth/github/callback',
  passport.authenticate('github', { failureRedirect: '/' }),
  (req, res) => {
    if (req.user) {
      // @ts-ignore
      res.redirect(`github-widgets://auth?token=${req.user.accessToken}`);
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

app.get('/', (req, res) => {
  res.send('Hello World');
});

app.listen(3000, () => {
  console.log('Server is running on http://localhost:3000');
});
