private static int offset(byte[] chunk,int idx){
  if (isAscii(chunk)) {
    return idx + 2;
  }
 else {
    return findNthPoint(chunk,idx);
  }
}
