/** 
 * This implementation checks for the resolution of a resource URL.
 * @see java.lang.ClassLoader#getResource(String)
 * @see java.lang.Class#getResource(String)
 */
@Override public boolean exists(){
  return (resolveURL() != null);
}
