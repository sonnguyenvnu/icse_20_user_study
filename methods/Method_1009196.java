private static void error(@NotNull MacroNameArity macroNameArity){
  com.intellij.openapi.diagnostic.Logger logger=com.intellij.openapi.diagnostic.Logger.getInstance(Decompiler.class);
  String fullUserMessage="No decompiler for MacroNameArity (" + macroNameArity + ")";
  logger.error(LogMessageEx.createEvent(fullUserMessage,Joiner.on("\n").join(new Throwable().getStackTrace()),fullUserMessage,null,Collections.<Attachment>emptyList()));
}
