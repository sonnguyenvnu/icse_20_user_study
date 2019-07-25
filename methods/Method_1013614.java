/** 
 * @param joinPoint Counts the invocation for the given joinPoint
 */
public static synchronized void entering(JoinPoint joinPoint){
  final Signature signature=joinPoint.getSignature();
  final String methodName=signature.toShortString();
  Integer invocedTimes=invoctions.get(methodName);
  if (invocedTimes == null) {
    invoctions.put(methodName,1);
  }
 else {
    int i=invocedTimes.intValue();
    invoctions.put(methodName,++i);
  }
}
