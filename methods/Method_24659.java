/** 
 * Build all the code for this sketch. In an advanced program, the returned class name could be different, which is why the className is set based on the return value. A compilation error will burp up a RunnerException. Setting purty to 'true' will cause exception line numbers to be incorrect. Unless you know the code compiles, you should first run the preprocessor with purty set to false to make sure there are no errors, then once successful, re-export with purty set to true.
 * @param buildPath Location to copy all the .java files
 * @return null if compilation failed, main class name if not
 */
public String preprocess(File srcFolder,boolean sizeWarning) throws SketchException {
  return preprocess(srcFolder,null,new PdePreprocessor(sketch.getName()),sizeWarning);
}
