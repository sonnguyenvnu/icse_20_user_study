/** 
 * Compares this object to the specified object.
 * @param obj the object to compare with.
 * @return <code>true</code> if the objects are the same;<code>false</code> otherwise.
 */
@Override public boolean equals(final Object obj){
  if (obj != null) {
    if (((Boolean)this.value).getClass() == obj.getClass()) {
      return value == ((Boolean)obj).booleanValue();
    }
    if (this.getClass() == obj.getClass()) {
      return value == ((MutableBoolean)obj).value;
    }
  }
  return false;
}
