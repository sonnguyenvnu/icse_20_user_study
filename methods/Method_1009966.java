/** 
 * Returns the number of samples represented in this histogram.  If you want to know how many centroids are being used, try centroids().size().
 * @return the number of samples that have been added.
 */
@Override public long size(){
  return count;
}
