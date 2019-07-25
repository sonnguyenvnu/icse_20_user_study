/** 
 * Transforms an Accumulo  {@link org.apache.accumulo.core.data.Key} into thecorresponding key for the Bloom filter. If the key does not correspond to either an  {@link uk.gov.gchq.gaffer.data.element.Entity} or an{@link uk.gov.gchq.gaffer.data.element.Edge} then an {@link java.io.IOException} willbe thrown by the method which will be caught and then  {@code null}is returned.
 */
@Override public org.apache.hadoop.util.bloom.Key transform(final Key key){
  return new org.apache.hadoop.util.bloom.Key(getVertexFromRangeKey(key.getRowData().getBackingArray()));
}
