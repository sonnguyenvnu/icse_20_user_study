/** 
 * Mount path for read-only access (ro)
 * @param path the root of device/filesystem to be mounted as ro
 */
private static void mountFileSystemRO(String path) throws ShellNotRunningException {
  String command="umount -r \"" + path + "\"";
  RootHelper.runShellCommand(command);
}
