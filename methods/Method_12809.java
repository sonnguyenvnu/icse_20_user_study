private static byte[] decode(String base64) throws Exception {
  return Base64.decode(base64.getBytes(),Base64.DEFAULT);
}
