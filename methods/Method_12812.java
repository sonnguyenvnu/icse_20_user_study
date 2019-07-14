@Override public String encrypt(String content) throws RemoteException {
  char[] chars=content.toCharArray();
  for (int i=0; i < chars.length; i++) {
    chars[i]^=SECRET_CODE;
  }
  return new String(chars);
}
