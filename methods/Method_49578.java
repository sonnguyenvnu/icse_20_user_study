/** 
 * Given a map produced by  {@link HTable#getRegionLocations()}, transform each key from an  {@link HRegionInfo} to a {@link KeyRange} expressing theregion's start and end key bounds using JanusGraph-partitioning-friendly conventions (start inclusive, end exclusive, zero bytes appended where necessary to make all keys at least 4 bytes long). <p/> This method iterates over the entries in its map parameter and performs the following conditional conversions on its keys. "Require" below means either a  {@link Preconditions} invocation or an assertion. HRegionInfosometimes returns start and end keys of zero length; this method replaces zero length keys with null before doing any of the checks described below. The parameter map and the values it contains are only read and never modified. <ul> <li>If an entry's HRegionInfo has null start and end keys, then first require that the parameter map is a singleton, and then return a single-entry map whose  {@code KeyRange} has start and end buffers thatare both four bytes of zeros.</li> <li>If the entry has a null end key (but non-null start key), put an equivalent entry in the result map with a start key identical to the input, except that zeros are appended to values less than 4 bytes long, and an end key that is four bytes of zeros. <li>If the entry has a null start key (but non-null end key), put an equivalent entry in the result map where the start key is four bytes of zeros, and the end key has zeros appended, if necessary, to make it at least 4 bytes long, after which one is added to the padded value in unsigned 32-bit arithmetic with overflow allowed.</li> <li>Any entry which matches none of the above criteria results in an equivalent entry in the returned map, except that zeros are appended to both keys to make each at least 4 bytes long, and the end key is then incremented as described in the last bullet point.</li> </ul> After iterating over the parameter map, this method checks that it either saw no entries with null keys, one entry with a null start key and a different entry with a null end key, or one entry with both start and end keys null. If any null keys are observed besides these three cases, the method will die with a precondition failure.
 * @param locations A list of HRegionInfo
 * @return JanusGraph-friendly expression of each region's rowkey boundaries
 */
private Map<KeyRange,ServerName> normalizeKeyBounds(List<HRegionLocation> locations){
  HRegionLocation nullStart=null;
  HRegionLocation nullEnd=null;
  ImmutableMap.Builder<KeyRange,ServerName> b=ImmutableMap.builder();
  for (  HRegionLocation location : locations) {
    HRegionInfo regionInfo=location.getRegionInfo();
    ServerName serverName=location.getServerName();
    byte startKey[]=regionInfo.getStartKey();
    byte endKey[]=regionInfo.getEndKey();
    if (0 == startKey.length) {
      startKey=null;
      logger.trace("Converted zero-length HBase startKey byte array to null");
    }
    if (0 == endKey.length) {
      endKey=null;
      logger.trace("Converted zero-length HBase endKey byte array to null");
    }
    if (null == startKey && null == endKey) {
      Preconditions.checkState(1 == locations.size());
      logger.debug("HBase table {} has a single region {}",tableName,regionInfo);
      return b.put(new KeyRange(FOUR_ZERO_BYTES,FOUR_ZERO_BYTES),serverName).build();
    }
 else     if (null == startKey) {
      logger.debug("Found HRegionInfo with null startKey on server {}: {}",serverName,regionInfo);
      Preconditions.checkState(null == nullStart);
      nullStart=location;
      StaticBuffer endBuf=StaticArrayBuffer.of(zeroExtend(endKey));
      b.put(new KeyRange(FOUR_ZERO_BYTES,endBuf),serverName);
    }
 else     if (null == endKey) {
      logger.debug("Found HRegionInfo with null endKey on server {}: {}",serverName,regionInfo);
      Preconditions.checkState(null == nullEnd);
      nullEnd=location;
      b.put(new KeyRange(StaticArrayBuffer.of(zeroExtend(startKey)),FOUR_ZERO_BYTES),serverName);
    }
 else {
      StaticBuffer startBuf=StaticArrayBuffer.of(zeroExtend(startKey));
      StaticBuffer endBuf=StaticArrayBuffer.of(zeroExtend(endKey));
      KeyRange kr=new KeyRange(startBuf,endBuf);
      b.put(kr,serverName);
      logger.debug("Found HRegionInfo with non-null end and start keys on server {}: {}",serverName,regionInfo);
    }
  }
  Preconditions.checkState((null == nullStart) == (null == nullEnd));
  Map<KeyRange,ServerName> result=b.build();
  for (  KeyRange kr : result.keySet()) {
    Preconditions.checkState(4 <= kr.getStart().length());
    Preconditions.checkState(4 <= kr.getEnd().length());
  }
  return result;
}
