/** 
 * Close the class pool
 */
public void close(){
  this.removeClassPath(classPath);
  classes.clear();
  softcache.clear();
}
