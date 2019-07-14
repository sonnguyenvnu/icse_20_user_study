@Override public void removeProfileBanner() throws TwitterException {
  post(conf.getRestBaseURL() + "account/remove_profile_banner.json");
}
