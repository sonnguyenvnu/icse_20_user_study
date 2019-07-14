/** 
 * Enable sharing of the "best" class-loader with 3rd party.
 * @return the class-loader user be the helper.
 */
public ClassLoader getClassLoader(){
  return (this.bestCandidate == null) ? Thread.currentThread().getContextClassLoader() : this.bestCandidate.getClassLoader();
}
