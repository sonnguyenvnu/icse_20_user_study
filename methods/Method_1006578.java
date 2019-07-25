/** 
 * Generates source code for an autocompletion bash script for the specified picocli-based application, and writes this script to the specified  {@code out} file, and optionally writes an invocation scriptto the specified  {@code command} file.
 * @param scriptName the name of the command to generate a bash autocompletion script for
 * @param commandLine the {@code CommandLine} instance for the command line application
 * @param out the file to write the autocompletion bash script source code to
 * @param command the file to write a helper script to that invokes the command, or {@code null} if no helper script file should be written
 * @throws IOException if a problem occurred writing to the specified files
 */
public static void bash(String scriptName,File out,File command,CommandLine commandLine) throws IOException {
  String autoCompleteScript=bash(scriptName,commandLine);
  Writer completionWriter=null;
  Writer scriptWriter=null;
  try {
    completionWriter=new FileWriter(out);
    completionWriter.write(autoCompleteScript);
    if (command != null) {
      scriptWriter=new FileWriter(command);
      scriptWriter.write("" + "#!/usr/bin/env bash\n" + "\n" + "LIBS=path/to/libs\n" + "CP=\"${LIBS}/myApp.jar\"\n" + "java -cp \"${CP}\" '" + commandLine.getCommand().getClass().getName() + "' $@");
    }
  }
  finally {
    if (completionWriter != null) {
      completionWriter.close();
    }
    if (scriptWriter != null) {
      scriptWriter.close();
    }
  }
}
