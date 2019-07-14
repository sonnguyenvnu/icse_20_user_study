/** 
 * nmon????
 * @return
 */
private String getStartServerCollect(){
  return getNmonFile() + " -F " + NMON_LOG + " -s0 -c1;" + "/bin/grep TCP /proc/net/sockstat > " + SOCK_LOG + ";ulimit -n -u > " + ULIMIT_LOG;
}
