/** 
 * Returns whether there have been class level Lombok annotations found. Note: this can only be queried after the class declaration node has been processed.
 * @return <code>true</code> if a lombok annotation at the class level hasbeen found
 */
protected boolean hasClassLombokAnnotation(){
  return classHasLombokAnnotation;
}
