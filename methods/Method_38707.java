/** 
 * Reuses this instance for better performances.
 */
public void reuse(final Object value){
  this.value=value;
  this.propertyName=null;
  this.index=0;
}
