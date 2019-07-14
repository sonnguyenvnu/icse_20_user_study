/** 
 * ??????
 * @param ip
 * @param session
 */
private void collectServerStatus(String ip,SSHSession session){
  final Server server=new Server();
  server.setIp(ip);
  Result result=session.executeCommand(COLLECT_SERVER_STATUS,new DefaultLineProcessor(){
    public void process(    String line,    int lineNum) throws Exception {
      server.parse(line,null);
    }
  }
);
  if (!result.isSuccess()) {
    logger.error("collect " + ip + " err:" + result.getResult(),result.getExcetion());
  }
  serverDataService.saveAndUpdateServerInfo(server);
  serverDataService.saveServerStat(server);
}
