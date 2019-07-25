public static Object count(final Object column){
  return new Function(COUNT,convertToColumn(column));
}
