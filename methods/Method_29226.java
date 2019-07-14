public static String getSentinelShell(int port){
  return String.format(SENTINEL_SHELL,MachineProtocol.CONF_DIR + getConfig(port,false),port,DateUtil.formatYYYYMMddHHMM(new Date()));
}
