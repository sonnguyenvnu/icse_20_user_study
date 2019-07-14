/** 
 * Returns <code>true</code> if entity is persistent.
 */
protected <E>boolean isPersistent(final DbEntityDescriptor<E> ded,final E entity){
  final Object key=ded.getIdValue(entity);
  if (key == null) {
    return false;
  }
  if (key instanceof Number) {
    final long value=((Number)key).longValue();
    if (value == 0) {
      return false;
    }
  }
  return true;
}
