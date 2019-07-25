public static boolean match(final Field field1,final Field field2){
  if (field1.getName().equals(field2.getName()) && field1.getType().equals(field2.getType())) {
    return true;
  }
 else {
    return false;
  }
}
