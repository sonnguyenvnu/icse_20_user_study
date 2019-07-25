private static void initialize() throws Exception {
  sSNIHostNameConstructor=Misc.getConstructor("javax.net.ssl.SNIHostName",new Class<?>[]{String.class});
  sSetServerNamesMethod=Misc.getMethod("javax.net.ssl.SSLParameters","setServerNames",new Class<?>[]{List.class});
}
