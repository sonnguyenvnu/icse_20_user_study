void mdhd(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  byte version=atom.readByte();
  atom.skip(3);
  atom.skip(version == 1 ? 16 : 8);
  int sampleRate=atom.readInt();
  long samples=version == 1 ? atom.readLong() : atom.readInt();
  if (duration == 0) {
    duration=1000 * samples / sampleRate;
  }
 else   if (LOGGER.isLoggable(debugLevel) && Math.abs(duration - 1000 * samples / sampleRate) > 2) {
    LOGGER.log(debugLevel,"mdhd: duration " + duration + " -> " + (1000 * samples / sampleRate));
  }
}
