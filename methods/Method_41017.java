public List<Matcher<TriggerKey>> getTriggerListenerMatchers(String listenerName){
synchronized (globalTriggerListeners) {
    List<Matcher<TriggerKey>> matchers=globalTriggerListenersMatchers.get(listenerName);
    if (matchers == null)     return null;
    return Collections.unmodifiableList(matchers);
  }
}
