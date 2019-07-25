@Override public ProxyOptionParser read(String option){
  String[] optionArr=StringUtil.splitRemoveEmpty(AbstractProxyOptionParser.WHITE_SPACE,option);
  subOptionParser=getSubOptionParser(optionArr[1]);
  String[] suboption;
  if (optionArr.length > 2) {
    suboption=new String[optionArr.length - 2];
    System.arraycopy(optionArr,2,suboption,0,suboption.length);
  }
 else {
    suboption=new String[0];
  }
  subOptionParser.parse(suboption);
  return this;
}
