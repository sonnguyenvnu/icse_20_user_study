private static Params parseCommand(String[] args){
  try {
    Options options=new Options();
    options.addOption(new Option("l","language",true,"language"));
    options.addOption(new Option("t","thread",true,"thread"));
    options.addOption(new Option("f","file",true,"script file"));
    options.addOption(new Option("i","input",true,"input file"));
    options.addOption(new Option("s","sleep",true,"sleep time"));
    options.addOption(new Option("g","logger",true,"sleep time"));
    CommandLineParser commandLineParser=new PosixParser();
    CommandLine commandLine=commandLineParser.parse(options,args);
    return readOptions(commandLine);
  }
 catch (  Exception e) {
    e.printStackTrace();
    exit();
    return null;
  }
}
