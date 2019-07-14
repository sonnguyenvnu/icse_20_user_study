public static int b3StateLoggingSetDeviceTypeFilter(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int deviceTypeFilter){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3StateLoggingSetDeviceTypeFilter(commandHandle,deviceTypeFilter);
}
