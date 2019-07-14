void ilst(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  while (atom.hasMoreChildren()) {
    MP4Atom child=atom.nextChild();
    if (LOGGER.isLoggable(debugLevel)) {
      LOGGER.log(debugLevel,child.toString());
    }
    if (child.getRemaining() == 0) {
      if (LOGGER.isLoggable(debugLevel)) {
        LOGGER.log(debugLevel,child.getPath() + ": contains no value");
      }
      continue;
    }
    data(child.nextChildUpTo("data"));
  }
}
