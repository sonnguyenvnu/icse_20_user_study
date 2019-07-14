public static MacOSXLibraryBundle getWithIdentifier(String bundleID){
  long filePath=NULL;
  try (MemoryStack stack=stackPush()){
    filePath=CString2CFString(stack.ASCII(bundleID),kCFStringEncodingASCII);
    long bundleRef=CFBundleGetBundleWithIdentifier(filePath);
    if (bundleRef == NULL) {
      throw new UnsatisfiedLinkError("Failed to retrieve bundle with identifier: " + bundleID);
    }
    CFRetain(bundleRef);
    return new MacOSXLibraryBundle(bundleID,bundleRef);
  }
  finally {
    if (filePath != NULL) {
      CFRelease(filePath);
    }
  }
}
