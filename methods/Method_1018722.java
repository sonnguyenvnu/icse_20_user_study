@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  String subCommand=safeReadLine(reader);
  boolean unknownSubCommand=false;
  String param=reader.readLine();
  String returnCommand=null;
  try {
    final String[] names;
    if (subCommand.equals(DIR_FIELDS_SUBCOMMAND_NAME)) {
      Object targetObject=gateway.getObject(param);
      names=reflectionEngine.getPublicFieldNames(targetObject);
    }
 else     if (subCommand.equals(DIR_METHODS_SUBCOMMAND_NAME)) {
      Object targetObject=gateway.getObject(param);
      names=reflectionEngine.getPublicMethodNames(targetObject);
    }
 else     if (subCommand.equals(DIR_STATIC_SUBCOMMAND_NAME)) {
      Class<?> clazz=TypeUtil.forName(param);
      names=reflectionEngine.getPublicStaticNames(clazz);
    }
 else     if (subCommand.equals(DIR_JVMVIEW_SUBCOMMAND_NAME)) {
      names=getJvmViewNames(param,reader);
    }
 else {
      names=null;
      unknownSubCommand=true;
    }
    reader.readLine();
    if (unknownSubCommand) {
      returnCommand=Protocol.getOutputErrorCommand("Unknown Array SubCommand Name: " + subCommand);
    }
 else     if (names == null) {
      ReturnObject returnObject=gateway.getReturnObject(null);
      returnCommand=Protocol.getOutputCommand(returnObject);
    }
 else {
      StringBuilder namesJoinedBuilder=new StringBuilder();
      for (      String name : names) {
        namesJoinedBuilder.append(name);
        namesJoinedBuilder.append("\n");
      }
      final String namesJoined;
      if (namesJoinedBuilder.length() > 0) {
        namesJoined=namesJoinedBuilder.substring(0,namesJoinedBuilder.length() - 1);
      }
 else {
        namesJoined="";
      }
      ReturnObject returnObject=gateway.getReturnObject(namesJoined);
      returnCommand=Protocol.getOutputCommand(returnObject);
    }
  }
 catch (  Exception e) {
    logger.log(Level.FINEST,"Error in a dir subcommand",e);
    returnCommand=Protocol.getOutputErrorCommand();
  }
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
