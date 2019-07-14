private static Params readOptions(CommandLine commandLine){
  Params params=new Params();
  if (commandLine.hasOption("l")) {
    String language=commandLine.getOptionValue("l");
    params.setLanguagefromArg(language);
  }
  if (commandLine.hasOption("f")) {
    String scriptFilename=commandLine.getOptionValue("f");
    params.setScriptFileName(scriptFilename);
  }
 else {
    exit();
  }
  if (commandLine.hasOption("s")) {
    Integer sleepTime=Integer.parseInt(commandLine.getOptionValue("s"));
    params.setSleepTime(sleepTime);
  }
  if (commandLine.hasOption("t")) {
    Integer thread=Integer.parseInt(commandLine.getOptionValue("t"));
    params.setThread(thread);
  }
  if (commandLine.hasOption("g")) {
    configLogger(commandLine.getOptionValue("g"));
  }
  params.setUrls(commandLine.getArgList());
  return params;
}
