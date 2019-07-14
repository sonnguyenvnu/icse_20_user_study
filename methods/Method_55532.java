@Override public long getFunctionAddress(ByteBuffer functionName){
  long nameRef=CString2CFString(functionName,kCFStringEncodingASCII);
  try {
    return CFBundleGetFunctionPointerForName(address(),nameRef);
  }
  finally {
    CFRelease(nameRef);
  }
}
