/** 
 * Creates a deep copy of this template.
 * @return A deep copy of this template.
 */
@Override public Object clone(){
  try {
    return super.clone();
  }
 catch (  CloneNotSupportedException e) {
    throw new InternalError("CodeTemplate implementation not Cloneable: " + getClass().getName());
  }
}
