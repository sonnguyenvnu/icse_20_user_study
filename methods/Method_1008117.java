@Override protected void execute(Terminal terminal,OptionSet options) throws Exception {
  if (subcommands.isEmpty()) {
    throw new IllegalStateException("No subcommands configured");
  }
  String[] args=arguments.values(options).toArray(new String[0]);
  if (args.length == 0) {
    throw new UserException(ExitCodes.USAGE,"Missing command");
  }
  Command subcommand=subcommands.get(args[0]);
  if (subcommand == null) {
    throw new UserException(ExitCodes.USAGE,"Unknown command [" + args[0] + "]");
  }
  subcommand.mainWithoutErrorHandling(Arrays.copyOfRange(args,1,args.length),terminal);
}
