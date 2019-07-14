private static int readTrailer(byte[] data,int index,int bytes){
  return SnappyInternalUtils.loadInt(data,index) & wordmask[bytes];
}
