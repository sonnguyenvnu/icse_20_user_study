/** 
 * Resolve <code>~</code> in the path.
 */
public static String resolveHome(final String path){
  if (path.length() == 1) {
    if (path.charAt(0) == '~') {
      return SystemUtil.info().getHomeDir();
    }
    return path;
  }
  if (path.length() >= 2) {
    if ((path.charAt(0) == '~') && (path.charAt(1) == File.separatorChar)) {
      return SystemUtil.info().getHomeDir() + path.substring(1);
    }
  }
  return path;
}
