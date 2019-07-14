public static boolean isClob(Class clazz){
  if (class_Clob == null && !class_Clob_error) {
    try {
      class_Clob=Class.forName("java.sql.Clob");
    }
 catch (    Throwable ex) {
      class_Clob_error=true;
    }
  }
  if (class_Clob == null) {
    return false;
  }
  return class_Clob.isAssignableFrom(clazz);
}
