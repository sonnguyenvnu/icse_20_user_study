/** 
 * <p> Parses the command line and initializes  {@code @Field} variablesannotated with  {@code @Option} or {@code @Parameters} before executing the script body.Also takes care of error handling and common use cases like requests for usage help. </p><p> Here is a break-down of the steps the base class takes before the statements in the script body are executed: </p> <ol> <li>A new  {@link CommandLine} is created with this script instance as the annotated command object.The  {@code CommandLine} instance is cached in the {@code commandLine} property(so it can be referred to in script code with {@code this.commandLine}).  {@code CommandLine} creation and initialization may becustomized by overriding  {@link #createCommandLine()}.</li> <li>The  {@link #parseScriptArguments(CommandLine,String[])} method is called with the script arguments.This initialises all  {@code @Field} variables annotated with {@link Option} or{@link Parameters}, unless the user input was invalid.</li> <li>If the user input was invalid, an error message and the usage message are printed to standard err and the script exits. This may be customized by overriding {@link #handleParameterException(CommandLine.ParameterException,String[])}.</li> <li>Otherwise, if the user input requested version help or usage help, the version string or usage help message is printed to standard err and the script exits.</li> <li>If the script implements  {@code Runnable} or {@code Callable}, its  {@code run} (or {@code call}) method is called. The script may support subcommands. In that case only the last specified subcommand is run or called if it implements  {@code Runnable} or {@code Callable}. This may be customized by overriding {@link #runRunnableSubcommand(List)}.</li> <li>Finally, the script body is executed.</li> </ol>
 * @return The result of the script evaluation.
 */
@Override public Object run(){
  String[] args=getScriptArguments();
  CommandLine commandLine=getOrCreateCommandLine();
  List<CommandLine> parsedCommands=null;
  try {
    parsedCommands=parseScriptArguments(commandLine,args);
  }
 catch (  ParameterException pe) {
    return handleParameterException(pe,args);
  }
  try {
    for (    CommandLine parsed : parsedCommands) {
      if (parsed.isUsageHelpRequested()) {
        return printHelpMessage(parsed,System.out);
      }
      if (parsed.isVersionHelpRequested()) {
        return printVersionHelpMessage(parsed);
      }
    }
    runRunnableSubcommand(parsedCommands);
    return runScriptBody();
  }
 catch (  Exception ex) {
    return handleExecutionException(commandLine,args,ex);
  }
}
