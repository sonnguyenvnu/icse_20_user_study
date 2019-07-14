/** 
 * Specify default values.
 * @param val List of values
 * @return The same builder
 */
@SuppressWarnings("unchecked") public T defaultValues(V... val){
  this.defaultValues=Arrays.asList(val);
  return (T)this;
}
