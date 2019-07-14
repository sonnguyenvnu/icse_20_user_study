@SuppressWarnings("unchecked") public <R>R callByCaller(@Nullable Object caller,@Nullable Object... args) throws ReflectedException {
  check(caller,mMethod,"Method");
  try {
    return (R)mMethod.invoke(caller,args);
  }
 catch (  InvocationTargetException e) {
    throw new ReflectedException("Oops!",e.getTargetException());
  }
catch (  Throwable e) {
    throw new ReflectedException("Oops!",e);
  }
}
