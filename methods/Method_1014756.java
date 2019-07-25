private String host(){
  String port=env.getProperty("server.port");
  if (ProfileUtils.isDev()) {
    return "localhost:" + port;
  }
  return "test.toutiao.im";
}
