protected void dispose(T item){
  try {
    disposer.execute(item);
  }
 catch (  Exception e) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("exception raised disposing of " + item + " - will be ignored",e);
    }
  }
}
