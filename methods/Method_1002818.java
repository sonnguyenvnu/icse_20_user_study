/** 
 * Sets whether to set the  {@code "date"} header automatically. By default, the {@code "date"} header isset automatically.
 */
public final B date(boolean dateEnabled){
  this.dateEnabled=dateEnabled;
  return self();
}
