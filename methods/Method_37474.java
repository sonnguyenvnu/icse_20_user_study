/** 
 * Splits filename into a array of four Strings containing prefix, path, basename and extension. Path will contain ending separator.
 */
public static String[] split(final String filename){
  String prefix=getPrefix(filename);
  if (prefix == null) {
    prefix=StringPool.EMPTY;
  }
  int lastSeparatorIndex=indexOfLastSeparator(filename);
  int lastExtensionIndex=indexOfExtension(filename);
  String path;
  String baseName;
  String extension;
  if (lastSeparatorIndex == -1) {
    path=StringPool.EMPTY;
    if (lastExtensionIndex == -1) {
      baseName=filename.substring(prefix.length());
      extension=StringPool.EMPTY;
    }
 else {
      baseName=filename.substring(prefix.length(),lastExtensionIndex);
      extension=filename.substring(lastExtensionIndex + 1);
    }
  }
 else {
    path=filename.substring(prefix.length(),lastSeparatorIndex + 1);
    if (lastExtensionIndex == -1) {
      baseName=filename.substring(prefix.length() + path.length());
      extension=StringPool.EMPTY;
    }
 else {
      baseName=filename.substring(prefix.length() + path.length(),lastExtensionIndex);
      extension=filename.substring(lastExtensionIndex + 1);
    }
  }
  return new String[]{prefix,path,baseName,extension};
}
