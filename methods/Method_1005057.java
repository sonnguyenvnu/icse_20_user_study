/** 
 * Transforms a  {@link org.apache.accumulo.core.data.Range} into aBloomFilter key. If the first vertices in the start and end keys of the range are the same, then we can create the appropriate BloomFilter key. If the key does not correspond to either an {@link uk.gov.gchq.gaffer.data.element.Entity} or an {@link uk.gov.gchq.gaffer.data.element.Edge}then we return  {@code null} to indicate that the range cannot beconverted into a single key for the Bloom filter.
 */
@Override public org.apache.hadoop.util.bloom.Key transform(final Range range){
  if (null == range.getStartKey() || null == range.getEndKey()) {
    return null;
  }
  final byte[] startKeyFirstIdentifier=getVertexFromRangeKey(range.getStartKey().getRowData().getBackingArray());
  final byte[] endKeyFirstIdentifier=getVertexFromRangeKey(range.getEndKey().getRowData().getBackingArray());
  if (Arrays.equals(startKeyFirstIdentifier,endKeyFirstIdentifier)) {
    return new org.apache.hadoop.util.bloom.Key(startKeyFirstIdentifier);
  }
  return null;
}
