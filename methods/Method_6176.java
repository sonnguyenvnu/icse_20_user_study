void trak(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  mdia(atom.nextChildUpTo("mdia"));
}
