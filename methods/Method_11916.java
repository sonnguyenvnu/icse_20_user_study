private static List<String> getCauseStackTraceLines(Throwable exception){
  if (exception.getCause() != null || hasSuppressed(exception)) {
    String fullTrace=getFullStackTrace(exception);
    BufferedReader reader=new BufferedReader(new StringReader(fullTrace.substring(exception.toString().length())));
    List<String> causedByLines=new ArrayList<String>();
    try {
      String line;
      while ((line=reader.readLine()) != null) {
        if (line.startsWith("Caused by: ") || line.trim().startsWith("Suppressed: ")) {
          causedByLines.add(line);
          while ((line=reader.readLine()) != null) {
            causedByLines.add(line);
          }
          return causedByLines;
        }
      }
    }
 catch (    IOException e) {
    }
  }
  return Collections.emptyList();
}
