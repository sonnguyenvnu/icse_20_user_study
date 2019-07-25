@Override public String invoke(String text){
  Principal caller=context.getCallerPrincipal();
  LOGGER.info("[" + caller.getName() + "] " + text);
  return "app1[" + caller.getName() + "]@" + getJBossNodeName();
}
