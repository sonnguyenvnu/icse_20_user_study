public static byte[] encode(String s){
  return s == null ? new byte[0] : s.getBytes(RpcConstants.DEFAULT_CHARSET);
}
