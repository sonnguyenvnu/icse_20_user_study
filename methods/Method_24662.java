protected int findErrorFile(int errorLine){
  for (int i=sketch.getCodeCount() - 1; i > 0; i--) {
    SketchCode sc=sketch.getCode(i);
    if (sc.isExtension("pde") && (sc.getPreprocOffset() <= errorLine)) {
      return i;
    }
  }
  return 0;
}
