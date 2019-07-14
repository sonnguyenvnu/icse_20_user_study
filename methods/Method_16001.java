public static Map<String,Object> getArgsMap(JoinPoint pjp){
  MethodSignature signature=(MethodSignature)pjp.getSignature();
  Map<String,Object> args=new LinkedHashMap<>();
  String names[]=signature.getParameterNames();
  for (int i=0, len=names.length; i < len; i++) {
    args.put(names[i],pjp.getArgs()[i]);
  }
  return args;
}
