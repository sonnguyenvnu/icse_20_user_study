/** 
 * Check if this resource references a bucket and not a blob.
 * @return if the resource is bucket
 */
public boolean isBucket(){
  return this.objectKey == null;
}
