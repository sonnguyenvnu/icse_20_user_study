@Override public AccountSettings updateAccountSettings(Integer trend_locationWoeid,Boolean sleep_timeEnabled,String start_sleepTime,String end_sleepTime,String time_zone,String lang) throws TwitterException {
  List<HttpParameter> profile=new ArrayList<HttpParameter>(6);
  if (trend_locationWoeid != null) {
    profile.add(new HttpParameter("trend_location_woeid",trend_locationWoeid));
  }
  if (sleep_timeEnabled != null) {
    profile.add(new HttpParameter("sleep_time_enabled",sleep_timeEnabled.toString()));
  }
  if (start_sleepTime != null) {
    profile.add(new HttpParameter("start_sleep_time",start_sleepTime));
  }
  if (end_sleepTime != null) {
    profile.add(new HttpParameter("end_sleep_time",end_sleepTime));
  }
  if (time_zone != null) {
    profile.add(new HttpParameter("time_zone",time_zone));
  }
  if (lang != null) {
    profile.add(new HttpParameter("lang",lang));
  }
  return factory.createAccountSettings(post(conf.getRestBaseURL() + "account/settings.json",profile.toArray(new HttpParameter[profile.size()])));
}
