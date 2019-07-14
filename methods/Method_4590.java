private long scaleTimecodeToUs(long unscaledTimecode) throws ParserException {
  if (timecodeScale == C.TIME_UNSET) {
    throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
  }
  return Util.scaleLargeTimestamp(unscaledTimecode,timecodeScale,1000);
}
