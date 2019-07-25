@Override public int execute(final PrintWriter out,final PrintWriter err) throws IOException {
  if (classfiles.isEmpty()) {
    out.println("[WARN] No class files provided.");
  }
 else {
    final Analyzer analyzer=new Analyzer(new ExecutionDataStore(),new Printer(out));
    for (    final File file : classfiles) {
      analyzer.analyzeAll(file);
    }
  }
  return 0;
}
