public static DebugParams OBJECT(String key){
  DebugParams debugParams=new DebugParams();
  debugParams.command=new String[]{"OBJECT",key};
  return debugParams;
}
