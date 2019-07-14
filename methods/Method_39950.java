/** 
 * Updates last modified date.
 */
public void updateLastModified(final long lastModified){
  lastModifiedCount++;
  this.lastModified=Math.max(this.lastModified,lastModified);
}
