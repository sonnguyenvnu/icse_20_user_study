@Override public User verifyCredentials() throws TwitterException {
  return super.fillInIDAndScreenName(new HttpParameter[]{new HttpParameter("include_email",conf.isIncludeEmailEnabled())});
}
