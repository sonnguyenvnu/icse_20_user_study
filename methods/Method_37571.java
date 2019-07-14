/** 
 * Resolves subpath in safer way. For some reason, if child starts with a separator it gets resolved as a full path, ignoring the base. This method acts different.
 */
public static Path resolve(final Path base,String child){
  if (StringUtil.startsWithChar(child,File.separatorChar)) {
    child=child.substring(1);
  }
  return base.resolve(child);
}
