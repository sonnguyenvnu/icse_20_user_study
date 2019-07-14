@Override public void updateProfileBanner(InputStream image) throws TwitterException {
  post(conf.getRestBaseURL() + "account/update_profile_banner.json",new HttpParameter("banner","banner",image));
}
