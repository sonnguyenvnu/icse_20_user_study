@Override public void start(){
  try {
    server=new Server(port);
    ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(accountOnSolidityServlet),"/walletsolidity/getaccount");
    context.addServlet(new ServletHolder(listWitnessesOnSolidityServlet),"/walletsolidity/listwitnesses");
    context.addServlet(new ServletHolder(getAssetIssueListOnSolidityServlet),"/walletsolidity/getassetissuelist");
    context.addServlet(new ServletHolder(getPaginatedAssetIssueListOnSolidityServlet),"/walletsolidity/getpaginatedassetissuelist");
    context.addServlet(new ServletHolder(getAssetIssueByNameOnSolidityServlet),"/walletsolidity/getassetissuebyname");
    context.addServlet(new ServletHolder(getAssetIssueByIdOnSolidityServlet),"/walletsolidity/getassetissuebyid");
    context.addServlet(new ServletHolder(getAssetIssueListByNameOnSolidityServlet),"/walletsolidity/getassetissuelistbyname");
    context.addServlet(new ServletHolder(getNowBlockOnSolidityServlet),"/walletsolidity/getnowblock");
    context.addServlet(new ServletHolder(getBlockByNumOnSolidityServlet),"/walletsolidity/getblockbynum");
    context.addServlet(new ServletHolder(getDelegatedResourceOnSolidityServlet),"/walletsolidity/getdelegatedresource");
    context.addServlet(new ServletHolder(getDelegatedResourceAccountIndexOnSolidityServlet),"/walletsolidity/getdelegatedresourceaccountindex");
    context.addServlet(new ServletHolder(getExchangeByIdOnSolidityServlet),"/walletsolidity/getexchangebyid");
    context.addServlet(new ServletHolder(listExchangesOnSolidityServlet),"/walletsolidity/listexchanges");
    context.addServlet(new ServletHolder(getAccountByIdOnSolidityServlet),"/walletsolidity/getaccountbyid");
    context.addServlet(new ServletHolder(getBlockByIdOnSolidityServlet),"/walletsolidity/getblockbyid");
    context.addServlet(new ServletHolder(getBlockByLimitNextOnSolidityServlet),"/walletsolidity/getblockbylimitnext");
    context.addServlet(new ServletHolder(getBlockByLatestNumOnSolidityServlet),"/walletsolidity/getblockbylatestnum");
    context.addServlet(new ServletHolder(getTransactionByIdOnSolidityServlet),"/walletsolidity/gettransactionbyid");
    context.addServlet(new ServletHolder(getTransactionInfoByIdOnSolidityServlet),"/walletsolidity/gettransactioninfobyid");
    context.addServlet(new ServletHolder(getTransactionCountByBlockNumOnSolidityServlet),"/walletsolidity/gettransactioncountbyblocknum");
    context.addServlet(new ServletHolder(getNodeInfoOnSolidityServlet),"/wallet/getnodeinfo");
    int maxHttpConnectNumber=Args.getInstance().getMaxHttpConnectNumber();
    if (maxHttpConnectNumber > 0) {
      server.addBean(new ConnectionLimit(maxHttpConnectNumber,server));
    }
    server.start();
  }
 catch (  Exception e) {
    logger.debug("IOException: {}",e.getMessage());
  }
}
