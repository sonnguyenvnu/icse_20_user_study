private static String secureHashKey(final CacheKey key) throws UnsupportedEncodingException {
  return SecureHashUtil.makeSHA1HashBase64(key.getUriString().getBytes("UTF-8"));
}
