@Override public String process() throws Throwable {
  Map<String,List<Biz>> bizSet=new HashMap<>();
  if (versionCommandOption != null) {
    String bizName=nameCommandOption.getArgs()[0];
    String bizVersion=versionCommandOption.getArgs()[0];
    if (bizManagerService.getBiz(bizName,bizVersion) != null) {
      bizSet.put(bizName,Collections.singletonList(bizManagerService.getBiz(bizName,bizVersion)));
    }
  }
 else   if (nameCommandOption != null) {
    String bizName=nameCommandOption.getArgs()[0];
    bizSet.put(bizName,bizManagerService.getBiz(bizName));
  }
 else {
    Set<String> bizNames=bizManagerService.getAllBizNames();
    for (    String bizName : bizNames) {
      bizSet.put(bizName,bizManagerService.getBiz(bizName));
    }
  }
  StringBuilder sb=new StringBuilder();
  sb.append(String.format("Biz count=%d",bizManagerService.getBizInOrder().size())).append("\n");
  for (  String bizName : bizSet.keySet()) {
    for (    Biz biz : bizSet.get(bizName)) {
      sb.append(String.format("bizName=\'%s\', bizVersion=\'%s\', bizState=\'%s\'",biz.getBizName(),biz.getBizVersion(),biz.getBizState())).append("\n");
    }
  }
  LOGGER.info(String.format("Check result is:%s",sb.toString()));
  return sb.toString();
}
