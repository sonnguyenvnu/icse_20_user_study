@Override protected String printType(CyclicTypeRecorder ctr){
  if (Analyzer.self.hasOption("debug")) {
    return "bool(" + value + ")";
  }
 else {
    return "bool";
  }
}
