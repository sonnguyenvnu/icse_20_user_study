@Override protected String printType(CyclicTypeRecorder ctr){
  if (Analyzer.self.hasOption("debug") && value != null) {
    return "str(" + value + ")";
  }
 else {
    return "str";
  }
}
