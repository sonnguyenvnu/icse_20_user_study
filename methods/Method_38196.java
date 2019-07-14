/** 
 * Lazy initialization of descriptor.
 */
protected void init(){
  if (columnDescriptors == null) {
    resolveColumnsAndProperties(type);
  }
}
