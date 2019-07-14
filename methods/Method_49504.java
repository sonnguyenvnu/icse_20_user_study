private <O>O constructFromStringArgument(Class<O> dataType,String arg){
  try {
    Constructor<O> ctor=dataType.getConstructor(String.class);
    return ctor.newInstance(arg);
  }
 catch (  Exception e) {
    log.error("Failed to parse configuration string \"{}\" into type {} due to the following reflection exception",arg,dataType,e);
    throw new RuntimeException(e);
  }
}
