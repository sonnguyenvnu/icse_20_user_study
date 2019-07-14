public void statusError(Exception exception){
  if (exception instanceof SketchException) {
    SketchException re=(SketchException)exception;
    int codeIndex=re.getCodeIndex();
    if (codeIndex != -1) {
      String filename=sketch.getCode(codeIndex).getFileName();
      int line=re.getCodeLine() + 1;
      int column=re.getCodeColumn() + 1;
      systemErr.println(filename + ":" + line + ":" + column + ":" + line + ":" + column + ":" + " " + re.getMessage());
    }
 else {
      exception.printStackTrace();
    }
  }
 else {
    exception.printStackTrace();
  }
}
