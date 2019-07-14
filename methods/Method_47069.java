/** 
 * Command is run from the root context (u:r:SuperSU0)
 * @param cmd the command
 */
public static void runShellCommand(String cmd) throws ShellNotRunningException {
  if (MainActivity.shellInteractive == null || !MainActivity.shellInteractive.isRunning())   throw new ShellNotRunningException();
  MainActivity.shellInteractive.addCommand(cmd);
  MainActivity.shellInteractive.waitForIdle();
}
