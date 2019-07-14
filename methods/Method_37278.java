/** 
 * Returns constructors collection. Creates new collection of first access.
 */
protected Ctors getCtors(){
  if (ctors == null) {
    ctors=new Ctors(this);
  }
  return ctors;
}
