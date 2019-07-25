public void run(){
  if (!eval())   return;
  try {
    String condition=condition();
    if (condition == null)     condition="executed rule " + name();
    sv.addCondition(condition);
    if (log.isTraceEnabled())     log.trace(sv.getLocalAddress() + ": executing rule " + name());
    trigger();
  }
 catch (  Throwable t) {
    log.error(sv.getLocalAddress() + ": failed executiong rule " + name(),t);
  }
}
