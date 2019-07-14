public static void rollbackGroup(String groupId){
  if (Objects.isNull(globalContext)) {
    globalContext=applicationContext.getBean(TCGlobalContext.class);
  }
  globalContext.setRollbackOnly(groupId);
}
