private void fill(final InputStream in,final byte[] buf,final int len) throws IOException {
  int s=0;
  while (s < len) {
    int i=in.read(buf,s,len - s);
    if (i <= 0) {
      throw new HttpException(ProxyInfo.ProxyType.SOCKS5,"stream is closed");
    }
    s+=i;
  }
}
