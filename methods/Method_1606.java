@VisibleForTesting public static boolean endsWithEOI(CloseableReference<PooledByteBuffer> bytesRef,int length){
  PooledByteBuffer buffer=bytesRef.get();
  return length >= 2 && buffer.read(length - 2) == (byte)JfifUtil.MARKER_FIRST_BYTE && buffer.read(length - 1) == (byte)JfifUtil.MARKER_EOI;
}
