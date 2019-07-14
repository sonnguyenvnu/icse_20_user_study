@Override public Map<String,RateLimitStatus> getRateLimitStatus(String... resources) throws TwitterException {
  return factory.createRateLimitStatuses(get(conf.getRestBaseURL() + "application/rate_limit_status.json?resources=" + StringUtil.join(resources)));
}
