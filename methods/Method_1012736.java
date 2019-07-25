/** 
 * Returns whether or not the matched extension of this format is among the list of supplied extensions.
 * @param extensions String of comma-separated extensions
 * @return True if this format matches an extension in the supplied lists,false otherwise.
 * @see #match(String)
 */
public boolean skip(String... extensions){
  for (  String extensionsString : extensions) {
    if (extensionsString == null) {
      continue;
    }
    if ("*".equals(extensionsString)) {
      return true;
    }
    String[] extensionsArray=extensionsString.split(",");
    for (    String extension : extensionsArray) {
      if (StringUtil.hasValue(extension) && extension.equalsIgnoreCase(matchedExtension)) {
        return true;
      }
    }
  }
  return false;
}
