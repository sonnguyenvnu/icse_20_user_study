@Override public String handleCommand(String cmdLine){
  try {
    Command command=commandParser.parseCommand(cmdLine);
    if (command == null || !command.validate()) {
      return String.format("????: %s",cmdLine);
    }
    LOGGER.info(String.format("Start to process command:\'%s\'.",cmdLine));
    return command.process();
  }
 catch (  Throwable throwable) {
    LOGGER.error(String.format("Process command:\'%s\' fail.",cmdLine),throwable);
    return String.format("Process command:\'%s\' fail. Please check jarslink log.",cmdLine);
  }
 finally {
    LOGGER.info(String.format("End to process command:\'%s\'.",cmdLine));
  }
}
