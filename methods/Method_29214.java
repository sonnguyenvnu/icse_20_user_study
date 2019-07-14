/** 
 * ???ip????port?????????check???????
 * @param ip    ip
 * @param port  port
 * @param shell shell??
 * @return ????true?????false?
 */
@Override public boolean startProcessAtPort(final String ip,final int port,final String shell){
  checkArgument(!Strings.isNullOrEmpty(ip),"invalid ip.");
  checkArgument(port > 0 && port < 65536,"invalid port");
  checkArgument(!Strings.isNullOrEmpty(shell),"invalid shell.");
  boolean success=true;
  try {
    SSHUtil.execute(ip,shell);
    success=isPortUsed(ip,port);
  }
 catch (  SSHException e) {
    logger.error("execute shell command error, ip: {}, port: {}, shell: {}",ip,port,shell);
    logger.error(e.getMessage(),e);
  }
  return success;
}
