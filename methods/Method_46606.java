private String getRpcBeanName(String transactionType,LCNCmdType cmdType){
  if (transactionType != null) {
    String name=String.format(RPC_BEAN_NAME_FORMAT,transactionType,cmdType.getCode());
    log.debug("getRpcBeanName->{}",name);
    return name;
  }
 else {
    String name=String.format(RPC_BEAN_NAME_FORMAT.replaceFirst("_%s",""),cmdType.getCode());
    log.debug("getRpcBeanName->{}",name);
    return name;
  }
}
