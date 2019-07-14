/** 
 * ????????????????
 * @param ip
 * @param cmd
 * @return
 * @throws SSHException
 */
public static String execute(String ip,String cmd) throws SSHException {
  int sshPort=SSHUtil.getSshPort(ip);
  return execute(ip,sshPort,ConstUtils.USERNAME,ConstUtils.PASSWORD,cmd);
}
