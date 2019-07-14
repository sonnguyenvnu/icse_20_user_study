@JsonAnySetter public void setDynamicProperty(String name,Object value){
  final Set<String> ccyCodeList=Currency.getAvailableCurrencyCodes();
  String[] nameArr=name.toUpperCase().split("_");
  String name1=nameArr[0];
  if (nameArr.length == 2) {
    String name2=nameArr[1];
    if (ccyCodeList.contains(name1) && ccyCodeList.contains(name2)) {
      base=name1;
      counter=name2;
      price=new BigDecimal(value.toString());
    }
  }
 else   if (nameArr.length == 1) {
    if (ccyCodeList.contains(name1)) {
      amounts.put(name1,new BigDecimal(value.toString()));
    }
  }
}
