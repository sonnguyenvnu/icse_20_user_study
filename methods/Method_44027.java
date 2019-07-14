@JsonAnySetter public void setDynamicProperty(String name,BigDecimal value){
  int idx=name.indexOf('_');
  if (idx < 0) {
  }
  String pre=name.substring(0,idx);
  String post=name.substring(idx + 1);
switch (post) {
case "fee":
    fees.put(pre,value);
  break;
case "balance":
getBalance(pre).balance=value;
break;
case "available":
getBalance(pre).available=value;
break;
case "reserved":
getBalance(pre).reserved=value;
break;
default :
break;
}
}
