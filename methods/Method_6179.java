void udta(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  while (atom.hasMoreChildren()) {
    MP4Atom child=atom.nextChild();
    if ("meta".equals(child.getType())) {
      meta(child);
      break;
    }
  }
}
