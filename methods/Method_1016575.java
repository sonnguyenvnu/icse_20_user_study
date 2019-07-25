/** 
 * Sets which files should be formatted.  Files to be formatted = (target - targetExclude). When this method is called multiple times, only the last call has any effect. FileCollections pass through raw. Strings are treated as the 'include' arg to fileTree, with project.rootDir as the dir. List<String> are treated as the 'includes' arg to fileTree, with project.rootDir as the dir. Anything else gets passed to getProject().files().
 */
public void target(Object... targets){
  this.target=parseTargets(targets);
}
