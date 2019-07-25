final void interrupt(String reason) throws SQLServerException {
  if (logger.isLoggable(Level.FINEST)) {
    logger.finest(toString() + " Ignoring interrupt of uninterruptable TDS command; Reason:" + reason);
  }
}
