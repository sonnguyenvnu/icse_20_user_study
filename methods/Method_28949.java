public static String getLogHost(){
  InetAddress address=LOCAL_ADDRESS;
  return address == null ? LOCALHOST : address.getHostAddress();
}
