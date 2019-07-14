@Override public Map<String,RateLimitStatus> getRateLimitStatus() throws TwitterException {
  return factory.createRateLimitStatuses(get(conf.getRestBaseURL() + "application/rate_limit_status.json"));
}
