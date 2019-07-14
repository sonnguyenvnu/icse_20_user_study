public static PMDParameters extractParameters(PMDParameters arguments,String[] args,String progName){
  JCommander jcommander=new JCommander(arguments);
  jcommander.setProgramName(progName);
  try {
    jcommander.parse(args);
    if (arguments.isHelp()) {
      jcommander.usage();
      System.out.println(buildUsageText(jcommander));
      setStatusCodeOrExit(ERROR_STATUS);
    }
  }
 catch (  ParameterException e) {
    jcommander.usage();
    System.out.println(buildUsageText(jcommander));
    System.err.println(e.getMessage());
    setStatusCodeOrExit(ERROR_STATUS);
  }
  return arguments;
}
