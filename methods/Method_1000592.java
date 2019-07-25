public String msg(){
  return format("Fail to invoke [%s].%s() by args:\n %s",typeName,methodName,safeConcat(args)) + "\nFor the reason: %s";
}
