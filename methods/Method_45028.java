private static Iterator<?> list(ClassLoader CL){
  Class<?> CL_class=CL.getClass();
  while (CL_class != java.lang.ClassLoader.class) {
    CL_class=CL_class.getSuperclass();
  }
  java.lang.reflect.Field ClassLoader_classes_field;
  try {
    ClassLoader_classes_field=CL_class.getDeclaredField("classes");
    ClassLoader_classes_field.setAccessible(true);
    Vector<?> classes=(Vector<?>)ClassLoader_classes_field.get(CL);
    return classes.iterator();
  }
 catch (  NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
  return null;
}
