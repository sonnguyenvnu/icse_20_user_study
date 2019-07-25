@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  char subCommand=safeReadLine(reader).charAt(0);
  String returnCommand=null;
  if (subCommand == GET_UNKNOWN_SUB_COMMAND_NAME) {
    returnCommand=getUnknownMember(reader);
  }
 else   if (subCommand == GET_JAVA_LANG_CLASS_SUB_COMMAND_NAME) {
    returnCommand=getJavaLangClass(reader);
  }
 else {
    returnCommand=getMember(reader);
  }
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
