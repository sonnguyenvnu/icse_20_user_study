/** 
 * Interns an element into this instance.
 * @param type {@code non-null;} the type to intern
 * @return {@code non-null;} the interned reference
 */
public synchronized TypeIdItem intern(Type type){
  if (type == null) {
    throw new NullPointerException("type == null");
  }
  throwIfPrepared();
  TypeIdItem result=typeIds.get(type);
  if (result == null) {
    result=new TypeIdItem(new CstType(type));
    typeIds.put(type,result);
  }
  return result;
}
