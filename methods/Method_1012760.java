public void flush(Logger rootLogger){
synchronized (eventListLock) {
    while (!eventList.isEmpty()) {
      rootLogger.callAppenders((ILoggingEvent)eventList.poll());
    }
  }
}
