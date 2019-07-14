public static Configuration wrapConfiguration(Configuration inner){
  final String className="org.apache.hadoop.hbase.HBaseConfiguration";
  final String methodName="create";
  final Class<?> methodArgType=Configuration.class;
  try {
    Class<?> clazz=HBaseAuthHelper.class.getClassLoader().loadClass(className);
    Method m=clazz.getMethod(methodName,methodArgType);
    return (Configuration)m.invoke(null,inner);
  }
 catch (  Throwable t) {
    log.error("Failed to call {}.{}({})",className,methodName,methodArgType.getSimpleName(),t);
  }
  return inner;
}
