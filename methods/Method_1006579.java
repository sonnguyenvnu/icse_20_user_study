/** 
 * Generates and returns the source code for an autocompletion bash script for the specified picocli-based application.
 * @param scriptName the name of the command to generate a bash autocompletion script for
 * @param commandLine the {@code CommandLine} instance for the command line application
 * @return source code for an autocompletion bash script
 */
public static String bash(String scriptName,CommandLine commandLine){
  if (scriptName == null) {
    throw new NullPointerException("scriptName");
  }
  if (commandLine == null) {
    throw new NullPointerException("commandLine");
  }
  StringBuilder result=new StringBuilder();
  result.append(format(HEADER,scriptName,CommandLine.VERSION));
  Map<CommandDescriptor,CommandLine> function2command=new LinkedHashMap<CommandDescriptor,CommandLine>();
  result.append(generateEntryPointFunction(scriptName,commandLine,function2command));
  for (  Map.Entry<CommandDescriptor,CommandLine> functionSpec : function2command.entrySet()) {
    CommandDescriptor descriptor=functionSpec.getKey();
    result.append(generateFunctionForCommand(descriptor.functionName,descriptor.commandName,functionSpec.getValue()));
  }
  result.append(format(FOOTER,scriptName));
  return result.toString();
}
