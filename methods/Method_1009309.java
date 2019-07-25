@NotNull public static com.intellij.execution.process.ProcessOutput execute(@NotNull GeneralCommandLine cmd,int timeout) throws ExecutionException {
  CapturingProcessHandler processHandler=new CapturingProcessHandler(cmd.createProcess());
  return timeout < 0 ? processHandler.runProcess() : processHandler.runProcess(timeout);
}
