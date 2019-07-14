public static String getMethodBody(JoinPoint pjp){
  StringBuilder methodName=new StringBuilder(pjp.getSignature().getName()).append("(");
  MethodSignature signature=(MethodSignature)pjp.getSignature();
  String[] names=signature.getParameterNames();
  Class[] args=signature.getParameterTypes();
  for (int i=0, len=args.length; i < len; i++) {
    if (i != 0) {
      methodName.append(",");
    }
    methodName.append(args[i].getSimpleName()).append(" ").append(names[i]);
  }
  return methodName.append(")").toString();
}
