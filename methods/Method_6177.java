void mdia(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  mdhd(atom.nextChild("mdhd"));
}
