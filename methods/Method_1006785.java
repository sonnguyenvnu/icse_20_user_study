private static int compare(String property,boolean isAsc,Map<String,Object> a,Map<String,Object> b){
  int scValue=isAsc ? 1 : -1;
  Object valueA=a.get(property);
  Object valueB=b.get(property);
  if (valueA instanceof String) {
    int intA=valueA.toString().charAt(0);
    int intB=valueB.toString().charAt(0);
    if (intA > intB)     return 1 * scValue;
    if (intA < intB)     return -1 * scValue;
    return 0;
  }
 else {
    BigDecimal bdA=new BigDecimal(valueA.toString().toCharArray());
    BigDecimal bdB=new BigDecimal(valueB.toString().toCharArray());
    return bdA.compareTo(bdB) * scValue;
  }
}
