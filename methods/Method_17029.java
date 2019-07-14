static Unsafe load(String openJdk,String android) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
  Field field;
  try {
    field=Unsafe.class.getDeclaredField(openJdk);
  }
 catch (  NoSuchFieldException e) {
    try {
      field=Unsafe.class.getDeclaredField(android);
    }
 catch (    NoSuchFieldException e2) {
      Constructor<Unsafe> unsafeConstructor=Unsafe.class.getDeclaredConstructor();
      unsafeConstructor.setAccessible(true);
      return unsafeConstructor.newInstance();
    }
  }
  field.setAccessible(true);
  return (Unsafe)field.get(null);
}
