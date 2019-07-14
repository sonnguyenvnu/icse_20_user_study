/** 
 * Returns file permissions in octal notation Method requires busybox
 */
private static int getFilePermissions(String path) throws ShellNotRunningException {
  String line=RootHelper.runShellCommandToList("stat -c  %a \"" + path + "\"").get(0);
  return Integer.valueOf(line);
}
