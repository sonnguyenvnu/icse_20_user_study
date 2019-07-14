public static void configureFor(int port){
  defaultInstance.set(WireMock.create().port(port).build());
}
