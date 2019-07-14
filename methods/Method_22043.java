/** 
 * ?????????.
 */
public void listen(){
  int port=configService.load(true).getMonitorPort();
  if (port < 0) {
    return;
  }
  try {
    log.info("Elastic job: Monitor service is running, the port is '{}'",port);
    openSocketForMonitor(port);
  }
 catch (  final IOException ex) {
    log.error("Elastic job: Monitor service listen failure, error is: ",ex);
  }
}
