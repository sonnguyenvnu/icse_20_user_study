/** 
 * ???Invocation???InvocationStat??????
 * @param stats Dimensions of invocation statistics
 * @return List<InvocationStat>
 */
public static List<InvocationStat> getInvocationStatSnapshots(List<InvocationStat> stats){
  List<InvocationStat> snapshots=new ArrayList<InvocationStat>(stats.size());
  for (  InvocationStat stat : stats) {
    InvocationStat snapshot=stat.snapshot();
    if (snapshot.getInvokeCount() <= 0) {
      if (stat.getUselessCycle().incrementAndGet() > 6) {
        InvocationStatFactory.removeInvocationStat(stat);
        InvocationStatDimension dimension=stat.getDimension();
        String appName=dimension.getAppName();
        if (LOGGER.isDebugEnabled(appName)) {
          LOGGER.debugWithApp(appName,"Remove invocation stat : {}, {} because of useless cycle > 6",dimension.getDimensionKey(),dimension.getProviderInfo());
        }
      }
    }
 else {
      stat.getUselessCycle().set(0);
      snapshots.add(snapshot);
    }
  }
  return snapshots;
}
