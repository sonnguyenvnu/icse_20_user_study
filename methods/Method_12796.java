public Reflector constructor(@Nullable Class<?>... parameterTypes) throws ReflectedException {
  try {
    mConstructor=mType.getDeclaredConstructor(parameterTypes);
    mConstructor.setAccessible(true);
    mField=null;
    mMethod=null;
    return this;
  }
 catch (  Throwable e) {
    throw new ReflectedException("Oops!",e);
  }
}
