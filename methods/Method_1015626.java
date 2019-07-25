protected static void invoke(String protocol_name,ResourceDMBean.Accessor setter,String attr,Object value){
  try {
    setter.invoke(value);
  }
 catch (  Exception e) {
    throw new RuntimeException(String.format("setting %s=%s failed in protocol %s: %s",attr,value,protocol_name,e));
  }
}
