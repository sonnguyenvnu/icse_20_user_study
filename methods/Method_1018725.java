@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  char subCommand=safeReadLine(reader).charAt(0);
  String returnCommand=null;
  if (subCommand == LIST_SLICE_SUB_COMMAND_NAME) {
    returnCommand=slice_list(reader);
  }
 else   if (subCommand == LIST_CONCAT_SUB_COMMAND_NAME) {
    returnCommand=concat_list(reader);
  }
 else   if (subCommand == LIST_MULT_SUB_COMMAND_NAME) {
    returnCommand=mult_list(reader);
  }
 else   if (subCommand == LIST_IMULT_SUB_COMMAND_NAME) {
    returnCommand=imult_list(reader);
  }
 else   if (subCommand == LIST_COUNT_SUB_COMMAND_NAME) {
    returnCommand=count_list(reader);
  }
 else {
    returnCommand=call_collections_method(reader,subCommand);
  }
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
