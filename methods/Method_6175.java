void mvhd(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  byte version=atom.readByte();
  atom.skip(3);
  atom.skip(version == 1 ? 16 : 8);
  int scale=atom.readInt();
  long units=version == 1 ? atom.readLong() : atom.readInt();
  if (duration == 0) {
    duration=1000 * units / scale;
  }
 else   if (LOGGER.isLoggable(debugLevel) && Math.abs(duration - 1000 * units / scale) > 2) {
    LOGGER.log(debugLevel,"mvhd: duration " + duration + " -> " + (1000 * units / scale));
  }
  speed=atom.readIntegerFixedPoint();
  volume=atom.readShortFixedPoint();
}
