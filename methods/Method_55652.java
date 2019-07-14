static void memCopyAligned(long src,long dst,int bytes){
  int i=0;
  for (; i <= bytes - 8; i+=8) {
    UNSAFE.putLong(null,dst + i,UNSAFE.getLong(null,src + i));
  }
  if (i < bytes) {
    UNSAFE.putLong(null,dst + i,merge(UNSAFE.getLong(null,src + i),UNSAFE.getLong(null,dst + i),SHIFT.right(-1L,bytes - i)));
  }
}
