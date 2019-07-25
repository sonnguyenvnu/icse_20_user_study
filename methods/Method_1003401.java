private void run(String... args) throws Exception {
  String dir=".";
  for (int i=0; i < args.length; i++) {
    if ("-dir".equals(args[i])) {
      dir=args[++i];
    }
  }
  process(dir + "/src");
  process(dir + "/docs");
}
