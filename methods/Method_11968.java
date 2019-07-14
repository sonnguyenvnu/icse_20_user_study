String[] parseOptions(String... args){
  for (int i=0; i != args.length; ++i) {
    String arg=args[i];
    if (arg.equals("--")) {
      return copyArray(args,i + 1,args.length);
    }
 else     if (arg.startsWith("--")) {
      if (arg.startsWith("--filter=") || arg.equals("--filter")) {
        String filterSpec;
        if (arg.equals("--filter")) {
          ++i;
          if (i < args.length) {
            filterSpec=args[i];
          }
 else {
            parserErrors.add(new CommandLineParserError(arg + " value not specified"));
            break;
          }
        }
 else {
          filterSpec=arg.substring(arg.indexOf('=') + 1);
        }
        filterSpecs.add(filterSpec);
      }
 else {
        parserErrors.add(new CommandLineParserError("JUnit knows nothing about the " + arg + " option"));
      }
    }
 else {
      return copyArray(args,i,args.length);
    }
  }
  return new String[]{};
}
