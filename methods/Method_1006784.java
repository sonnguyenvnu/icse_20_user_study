private static <T>int compare(Class clz,String orderBy,boolean isAsc,T a,T b){
  try {
    int scValue=isAsc ? 1 : -1;
    Field field=clz.getDeclaredField(orderBy);
    field.setAccessible(true);
    Object valueA=field.get(a);
    Object valueB=field.get(b);
    if (field.getType() == String.class) {
      int intA=valueA.toString().charAt(0);
      int intB=valueB.toString().charAt(0);
      if (intA > intB)       return 1 * scValue;
      if (intA < intB)       return -1 * scValue;
      return 0;
    }
 else {
      BigDecimal bdA=new BigDecimal(valueA.toString().toCharArray());
      BigDecimal bdB=new BigDecimal(valueB.toString().toCharArray());
      return bdA.compareTo(bdB) * scValue;
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return 0;
}
