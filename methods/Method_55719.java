@SuppressWarnings("unchecked") protected static <T extends Struct>T wrap(Class<T> clazz,long address,ByteBuffer container){
  T struct;
  try {
    struct=(T)UNSAFE.allocateInstance(clazz);
  }
 catch (  InstantiationException e) {
    throw new UnsupportedOperationException(e);
  }
  UNSAFE.putLong(struct,ADDRESS,address);
  UNSAFE.putObject(struct,CONTAINER,container);
  return struct;
}
