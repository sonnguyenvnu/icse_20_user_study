public List<Matcher<JobKey>> getJobListenerMatchers(String listenerName){
synchronized (globalJobListeners) {
    List<Matcher<JobKey>> matchers=globalJobListenersMatchers.get(listenerName);
    if (matchers == null)     return null;
    return Collections.unmodifiableList(matchers);
  }
}
