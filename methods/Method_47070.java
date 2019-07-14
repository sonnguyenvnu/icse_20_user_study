/** 
 * Runs the command on an interactive shell. Provides a listener for the caller to interact. The caller is executed on a worker background thread, hence any calls from the callback should be thread safe. Command is run from superuser context (u:r:SuperSU0)
 * @param cmd the command
 */
public static void runShellCommandWithCallback(String cmd,Shell.OnCommandResultListener callback) throws ShellNotRunningException {
  if (MainActivity.shellInteractive == null || !MainActivity.shellInteractive.isRunning())   throw new ShellNotRunningException();
  MainActivity.shellInteractive.addCommand(cmd,0,callback);
  MainActivity.shellInteractive.waitForIdle();
}
