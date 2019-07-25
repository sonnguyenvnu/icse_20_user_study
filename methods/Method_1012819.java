@Override public void run(){
  LOGGER.trace("Starting gathering of system information");
  logSystemInfo(Level.INFO);
  LOGGER.trace("Done logging system information, shutting down thread");
}
