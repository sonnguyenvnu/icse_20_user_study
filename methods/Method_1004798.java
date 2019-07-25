static int oversize(int minTargetSize,int bytesPerElement){
  if (minTargetSize < 0) {
    throw new EsHadoopIllegalArgumentException("invalid array size " + minTargetSize);
  }
  if (minTargetSize == 0) {
    return 0;
  }
  int extra=minTargetSize >> 3;
  if (extra < 3) {
    extra=3;
  }
  int newSize=minTargetSize + extra;
  if (newSize + 7 < 0) {
    return Integer.MAX_VALUE;
  }
  if (Constants.JRE_IS_64BIT) {
switch (bytesPerElement) {
case 4:
      return (newSize + 1) & 0x7ffffffe;
case 2:
    return (newSize + 3) & 0x7ffffffc;
case 1:
  return (newSize + 7) & 0x7ffffff8;
case 8:
default :
return newSize;
}
}
 else {
switch (bytesPerElement) {
case 2:
return (newSize + 1) & 0x7ffffffe;
case 1:
return (newSize + 3) & 0x7ffffffc;
case 4:
case 8:
default :
return newSize;
}
}
}
