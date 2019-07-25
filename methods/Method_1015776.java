public static String print(List<NetworkInterface> interfaces){
  return interfaces == null ? "null" : interfaces.stream().map(NetworkInterface::getName).collect(Collectors.joining(", "));
}
