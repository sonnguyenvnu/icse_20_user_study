private static long CString2CFString(ByteBuffer name,int encoding){
  return check(CFStringCreateWithCStringNoCopy(NULL,name,encoding,kCFAllocatorNull));
}
