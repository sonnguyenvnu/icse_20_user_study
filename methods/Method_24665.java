/** 
 * Map an error from a set of processed .java files back to its location in the actual sketch.
 * @param message The error message.
 * @param filename The .java file where the exception was found.
 * @param line Line number of the .java file for the exception (0-indexed!)
 * @return A RunnerException to be sent to the editor, or null if it wasn'tpossible to place the exception to the sketch code.
 */
public SketchException placeException(String message,String dotJavaFilename,int dotJavaLine){
  int codeIndex=0;
  int codeLine=-1;
  for (int i=0; i < sketch.getCodeCount(); i++) {
    SketchCode code=sketch.getCode(i);
    if (code.isExtension("java")) {
      if (dotJavaFilename.equals(code.getFileName())) {
        codeIndex=i;
        codeLine=dotJavaLine;
        return new SketchException(message,codeIndex,codeLine);
      }
    }
  }
  if (!dotJavaFilename.equals(sketch.getName() + ".java")) {
    return null;
  }
  codeIndex=0;
  for (int i=0; i < sketch.getCodeCount(); i++) {
    SketchCode code=sketch.getCode(i);
    if (code.isExtension("pde")) {
      if (code.getPreprocOffset() <= dotJavaLine) {
        codeIndex=i;
        codeLine=dotJavaLine - code.getPreprocOffset();
      }
    }
  }
  return new SketchException(message,codeIndex,codeLine,-1,false);
}
