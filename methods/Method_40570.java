/** 
 * Return absolute path for  {@code path}. Make sure path ends with "/" if it's a directory. Does _not_ resolve symlinks, since the caller may need to play symlink tricks to produce the desired paths for loaded modules.
 */
public static String canonicalize(String path){
  File f=new File(path);
  path=f.getAbsolutePath();
  if (f.isDirectory() && !path.endsWith("/")) {
    return path + "/";
  }
  return path;
}
