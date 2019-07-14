private static int ipToLong(final String ip) throws Exception {
  return ByteBuffer.wrap(InetAddress.getByName(ip).getAddress()).getInt();
}
