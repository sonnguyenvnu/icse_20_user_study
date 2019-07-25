/** 
 * Attempt to drop the capability to execute for the process. <p> This is best effort and OS and architecture dependent. It may throw any Throwable.
 * @return 0 if we can do this for application threads, 1 for the entire process
 */
static int init(Path tmpFile) throws Exception {
  if (Constants.LINUX) {
    return linuxImpl();
  }
 else   if (Constants.MAC_OS_X) {
    bsdImpl();
    macImpl(tmpFile);
    return 1;
  }
 else   if (Constants.SUN_OS) {
    solarisImpl();
    return 1;
  }
 else   if (Constants.FREE_BSD || OPENBSD) {
    bsdImpl();
    return 1;
  }
 else   if (Constants.WINDOWS) {
    windowsImpl();
    return 1;
  }
 else {
    throw new UnsupportedOperationException("syscall filtering not supported for OS: '" + Constants.OS_NAME + "'");
  }
}
