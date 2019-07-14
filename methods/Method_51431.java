/** 
 * Specify a default value. Some subclasses provide convenient related methods, see e.g.  {@link GenericCollectionPropertyBuilder#defaultValues(Object,Object[])}. Using the null value is prohibited. <p>Calling this method is required for  {@link #build()} to succeed.
 * @param val Default value
 * @return The same builder
 * @throws IllegalArgumentException If the argument is null
 */
@SuppressWarnings("unchecked") public B defaultValue(T val){
  if (val == null) {
    throw new IllegalArgumentException("Property values may not be null.");
  }
  this.defaultValue=val;
  return (B)this;
}
