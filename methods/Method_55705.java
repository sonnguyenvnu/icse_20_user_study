private static <T extends Buffer>long getParentOffset(int oopSize,T parent,Function<T,T> childFactory){
  T child=childFactory.apply(parent);
  long offset=oopSize;
switch (oopSize) {
case Integer.BYTES:
    while (true) {
      if (UNSAFE.getInt(parent,offset) != UNSAFE.getInt(child,offset)) {
        return offset;
      }
      offset+=oopSize;
    }
case Long.BYTES:
  while (true) {
    if (UNSAFE.getLong(parent,offset) != UNSAFE.getLong(child,offset)) {
      return offset;
    }
    offset+=oopSize;
  }
default :
throw new IllegalStateException();
}
}
