private static void handleEvent(final Subscriber subscriber,final Event event){
  try {
    subscriber.onEvent(event);
  }
 catch (  Throwable e) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Handle " + event.getClass() + " error",e);
    }
  }
}
