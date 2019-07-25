/** 
 * Interns an element into this instance.
 * @param prototype {@code non-null;} the prototype to intern
 * @return {@code non-null;} the interned reference
 */
public synchronized ProtoIdItem intern(Prototype prototype){
  if (prototype == null) {
    throw new NullPointerException("prototype == null");
  }
  throwIfPrepared();
  ProtoIdItem result=protoIds.get(prototype);
  if (result == null) {
    result=new ProtoIdItem(prototype);
    protoIds.put(prototype,result);
  }
  return result;
}
