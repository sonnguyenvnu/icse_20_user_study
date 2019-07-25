public static Object max(final Object column){
  return new Function(MAX,convertToColumn(column));
}
