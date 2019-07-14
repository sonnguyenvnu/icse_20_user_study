private void resolveUtcTimingElementDirect(UtcTimingElement timingElement){
  try {
    long utcTimestampMs=Util.parseXsDateTime(timingElement.value);
    onUtcTimestampResolved(utcTimestampMs - manifestLoadEndTimestampMs);
  }
 catch (  ParserException e) {
    onUtcTimestampResolutionError(e);
  }
}
