public void init(String host,int port) throws Exception {
  uri=new URI("http://" + host + ":" + port + "/");
  openBrowser();
}
