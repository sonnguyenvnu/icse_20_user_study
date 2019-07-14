/** 
 * Adds a number or string constant to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item.
 * @param cst the value of the constant to be added to the constant pool. This parameter must be an {@link Integer}, a  {@link Float}, a  {@link Long}, a  {@link Double}, a  {@link String} or a {@link Type}.
 * @return a new or already existing constant item with the given value.
 */
Item newConstItem(final Object cst){
  if (cst instanceof Integer) {
    int val=((Integer)cst).intValue();
    key.set(val);
    Item result=get(key);
    if (result == null) {
      pool.putByte(3).putInt(val);
      result=new Item(index++,key);
      put(result);
    }
    return result;
  }
 else   if (cst instanceof String) {
    return newString((String)cst);
  }
 else   if (cst instanceof Type) {
    Type t=(Type)cst;
    return newClassItem(t.sort == 10 ? t.getInternalName() : t.getDescriptor());
  }
 else {
    throw new IllegalArgumentException("value " + cst);
  }
}
