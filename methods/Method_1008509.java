/** 
 * The source of the document if exists.
 */
public byte[] source(){
  if (source == null) {
    return null;
  }
  if (sourceAsBytes != null) {
    return sourceAsBytes;
  }
  this.sourceAsBytes=BytesReference.toBytes(sourceRef());
  return this.sourceAsBytes;
}
