/** 
 * Preprocess and compile all the code for this sketch. In an advanced program, the returned class name could be different, which is why the className is set based on the return value. A compilation error will burp up a RunnerException.
 * @return null if compilation failed, main class name if not
 */
public String build(File srcFolder,File binFolder,boolean sizeWarning) throws SketchException {
  this.srcFolder=srcFolder;
  this.binFolder=binFolder;
  String classNameFound=preprocess(srcFolder,sizeWarning);
  if (Compiler.compile(this)) {
    sketchClassName=classNameFound;
    return classNameFound;
  }
  return null;
}
