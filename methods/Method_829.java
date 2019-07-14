private static boolean isKotlinIgnore(Class clazz,String methodName){
  if (kotlinIgnores == null && !kotlinIgnores_error) {
    try {
      Map<Class,String[]> map=new HashMap<Class,String[]>();
      Class charRangeClass=Class.forName("kotlin.ranges.CharRange");
      map.put(charRangeClass,new String[]{"getEndInclusive","isEmpty"});
      Class intRangeClass=Class.forName("kotlin.ranges.IntRange");
      map.put(intRangeClass,new String[]{"getEndInclusive","isEmpty"});
      Class longRangeClass=Class.forName("kotlin.ranges.LongRange");
      map.put(longRangeClass,new String[]{"getEndInclusive","isEmpty"});
      Class floatRangeClass=Class.forName("kotlin.ranges.ClosedFloatRange");
      map.put(floatRangeClass,new String[]{"getEndInclusive","isEmpty"});
      Class doubleRangeClass=Class.forName("kotlin.ranges.ClosedDoubleRange");
      map.put(doubleRangeClass,new String[]{"getEndInclusive","isEmpty"});
      kotlinIgnores=map;
    }
 catch (    Throwable error) {
      kotlinIgnores_error=true;
    }
  }
  if (kotlinIgnores == null) {
    return false;
  }
  String[] ignores=kotlinIgnores.get(clazz);
  return ignores != null && Arrays.binarySearch(ignores,methodName) >= 0;
}
