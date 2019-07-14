public SketchInterval mapJavaToSketch(IProblem iproblem){
  return mapJavaToSketch(iproblem.getSourceStart(),iproblem.getSourceEnd() + 1);
}
