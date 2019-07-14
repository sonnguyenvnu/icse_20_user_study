public static String getRunShell(int port,boolean isCluster){
  return String.format(RUN_SHELL,MachineProtocol.CONF_DIR + getConfig(port,isCluster),port,DateUtil.formatYYYYMMddHHMM(new Date()));
}
