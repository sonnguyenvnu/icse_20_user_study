@Override protected void execute(@Nonnull final String script) throws ScriptException {
  log.debug("Executing script:\n{}",script);
  final StringBuilder safeScriptBuilder=new StringBuilder(script.length());
  for (  final String line : script.split("\n")) {
    if (!LINE_CONTINUATION.matcher(line).find()) {
      safeScriptBuilder.append(';');
    }
    safeScriptBuilder.append(line);
  }
  final String safeScript=COMMENT.matcher(safeScriptBuilder.toString()).replaceAll("");
  for (  final Pattern pattern : getDenyPatterns()) {
    if (pattern.matcher(safeScript).find()) {
      log.error("Not executing script that matches deny pattern: {}",pattern);
      throw new ScriptException("Script not executed due to security policy.");
    }
  }
  final Thread thread=Thread.currentThread();
  final ClassLoader contextClassLoader=thread.getContextClassLoader();
  try {
    final IMain interpreter=getInterpreter();
    interpreter.setContextClassLoader();
    interpreter.interpret(safeScript);
  }
 catch (  final AssertionError e) {
    log.warn("Caught assertion error when executing script. Retrying...",e);
    reset();
    getInterpreter().interpret(safeScript);
  }
 finally {
    thread.setContextClassLoader(contextClassLoader);
  }
}
