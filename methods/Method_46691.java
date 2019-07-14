public String getServiceBeanName(LCNCmdType cmdType){
  return String.format(RPC_BEAN_NAME_FORMAT,cmdType.getCode());
}
