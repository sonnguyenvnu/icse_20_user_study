public static byte[] alloc(final int len){
  if ((len < minSize) || (len > maxSize))   return new byte[len];
  incAlive(len);
synchronized (objHeap) {
    final List<byte[]> buf=objHeap.get(Integer.valueOf(len));
    if (buf == null || buf.isEmpty())     return new byte[len];
    return buf.remove(buf.size() - 1);
  }
}
