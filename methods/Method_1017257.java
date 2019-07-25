@Override public String invoke(String text){
  Principal caller=context.getCallerPrincipal();
  LOGGER.info("[" + caller.getName() + "] " + text);
  return "app2[" + caller.getName() + "]@" + getJBossNodeName();
}
