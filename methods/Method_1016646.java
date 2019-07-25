public static Object min(final Object column){
  return new Function(MIN,convertToColumn(column));
}
