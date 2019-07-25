private String parse(String wow){
  WowCommonParser commonParser=new WowCommonParser(columns,defaultName);
  commonParser.parse(wow);
  return commonParser.toHql();
}
