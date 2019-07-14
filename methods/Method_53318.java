@Override public AccountSettings updateAllowDmsFrom(String allowDmsFrom) throws TwitterException {
  return factory.createAccountSettings(post(conf.getRestBaseURL() + "account/settings.json?allow_dms_from=" + allowDmsFrom));
}
