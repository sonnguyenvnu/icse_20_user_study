/** 
 * Gets the current source file.
 * @return the source file as a sequence of characters, or null if it is not available
 */
@Nullable public CharSequence getSourceCode(){
  try {
    return getPath().getCompilationUnit().getSourceFile().getCharContent(false);
  }
 catch (  IOException e) {
    return null;
  }
}
