/** 
 * Format a stack trace in a human-readable format.
 * @param throwable The exception/throwable whose stack trace to format.
 */
@Nullable public static String formatStacktrace(Throwable throwable){
  final StringWriter stringWriter=new StringWriter();
  final PrintWriter printWriter=new PrintWriter(stringWriter);
  String output=null;
  try {
    throwable.printStackTrace(printWriter);
  }
  finally {
    printWriter.close();
    try {
      output=stringWriter.toString();
      stringWriter.close();
    }
 catch (    final IOException ignored) {
    }
  }
  return output;
}
