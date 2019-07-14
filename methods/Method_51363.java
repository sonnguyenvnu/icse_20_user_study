/** 
 * Specify a default value.
 * @param val List of values
 * @return The same builder
 */
@SuppressWarnings("unchecked") public T defaultValues(Collection<? extends V> val){
  this.defaultValues=new ArrayList<>(val);
  return (T)this;
}
