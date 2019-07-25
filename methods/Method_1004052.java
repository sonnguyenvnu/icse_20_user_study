private IBundleCoverage analyze(final ExecutionDataStore data,final PrintWriter out) throws IOException {
  final CoverageBuilder builder=new CoverageBuilder();
  final Analyzer analyzer=new Analyzer(data,builder);
  for (  final File f : classfiles) {
    analyzer.analyzeAll(f);
  }
  printNoMatchWarning(builder.getNoMatchClasses(),out);
  return builder.getBundle(name);
}
