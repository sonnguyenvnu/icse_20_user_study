void meta(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  atom.skip(4);
  while (atom.hasMoreChildren()) {
    MP4Atom child=atom.nextChild();
    if ("ilst".equals(child.getType())) {
      ilst(child);
      break;
    }
  }
}
