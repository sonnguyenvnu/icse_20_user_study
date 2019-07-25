/** 
 * Overridable factory method used by sub-classes
 */
protected BeanPropertyWriter _new(PropertyName newName){
  if (getClass() != BeanPropertyWriter.class) {
    throw new IllegalStateException("Method must be overridden by " + getClass());
  }
  return new BeanPropertyWriter(this,newName);
}
