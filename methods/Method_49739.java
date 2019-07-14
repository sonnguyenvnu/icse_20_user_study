@Override public String getDeviceFFmpegVersion() throws FFmpegCommandAlreadyRunningException {
  ShellCommand shellCommand=new ShellCommand();
  CommandResult commandResult=shellCommand.runWaitFor(new String[]{FileUtils.getFFmpeg(context),"-version"});
  if (commandResult.success) {
    return commandResult.output.split(" ")[2];
  }
  return "";
}
