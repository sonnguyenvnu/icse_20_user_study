/** 
 * ??Invocation??InvocationStat ?Invocation???InvocationStat?????????????????????????Regulation???????????
 * @param statDimension InvocationStatDimension
 * @return InvocationStat
 */
public static InvocationStat getInvocationStat(InvocationStatDimension statDimension){
  InvocationStat invocationStat=ALL_STATS.get(statDimension);
  if (invocationStat == null) {
    invocationStat=new ServiceExceptionInvocationStat(statDimension);
    InvocationStat old=ALL_STATS.putIfAbsent(statDimension,invocationStat);
    if (old != null) {
      invocationStat=old;
    }
    for (    InvocationStatListener listener : LISTENERS) {
      listener.onAddInvocationStat(invocationStat);
    }
  }
  return invocationStat;
}
