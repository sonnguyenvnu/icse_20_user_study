private static void run(@NotNull GeneralCommandLine commandLine,@NotNull CompileContext context,@NotNull String builderName,@NotNull CompilerOptions compilerOptions) throws ProjectBuildException {
  Process process;
  try {
    process=commandLine.createProcess();
  }
 catch (  ExecutionException executionException) {
    throw new ProjectBuildException("Failed to run " + builderName,executionException);
  }
  BaseOSProcessHandler handler=new BaseOSProcessHandler(process,commandLine.getCommandLineString(),Charset.defaultCharset());
  com.intellij.execution.process.ProcessAdapter adapter=new ProcessAdapter(context,builderName,commandLine.getWorkDirectory().getPath(),compilerOptions);
  handler.addProcessListener(adapter);
  handler.startNotify();
  handler.waitFor();
}
