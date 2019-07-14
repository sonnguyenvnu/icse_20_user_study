@Override protected String printType(CyclicTypeRecorder ctr){
  StringBuilder sb=new StringBuilder("float");
  if (Analyzer.self.hasOption("debug")) {
    if (lower == upper) {
      sb.append("(" + lower + ")");
    }
 else     if (isLowerBounded() || isUpperBounded()) {
      sb.append("[");
      if (isLowerBounded()) {
        sb.append(lower);
      }
 else {
        sb.append("-?");
      }
      sb.append("..");
      if (isUpperBounded()) {
        sb.append(upper);
      }
 else {
        sb.append("+?");
      }
      sb.append("]");
    }
  }
  return sb.toString();
}
