static private JavaProblem convertIProblem(IProblem iproblem,PreprocessedSketch ps){
  SketchInterval in=ps.mapJavaToSketch(iproblem);
  if (in != SketchInterval.BEFORE_START) {
    String badCode=ps.getPdeCode(in);
    int line=ps.tabOffsetToTabLine(in.tabIndex,in.startTabOffset);
    JavaProblem p=JavaProblem.fromIProblem(iproblem,in.tabIndex,line,badCode);
    p.setPDEOffsets(in.startTabOffset,in.stopTabOffset);
    return p;
  }
  return null;
}
