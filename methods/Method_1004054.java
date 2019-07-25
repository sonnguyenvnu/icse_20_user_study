@Override public int execute(PrintWriter out,final PrintWriter err) throws Exception {
  final CommandParser mainParser=new CommandParser(this);
  try {
    mainParser.parseArgument(args);
  }
 catch (  final CmdLineException e) {
    ((CommandParser)e.getParser()).getCommand().printHelp(err);
    err.println();
    err.println(e.getMessage());
    return -1;
  }
  if (help) {
    printHelp(out);
    return 0;
  }
  if (command.help) {
    command.printHelp(out);
    return 0;
  }
  if (command.quiet) {
    out=NUL;
  }
  return command.execute(out,err);
}
