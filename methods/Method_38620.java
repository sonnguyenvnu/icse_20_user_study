/** 
 * Defines class and it's classloader to scan. This is not required in Java8 and would not hurt anything if called. However, for Java9, you should pass <i>any</i> user-application class, so Jodd can figure out the real class path to scan.
 */
@Override public JoyScanner scanClasspathOf(final Class applicationClass){
  requireNotStarted(classScanner);
  appClasses.add(applicationClass);
  return this;
}
