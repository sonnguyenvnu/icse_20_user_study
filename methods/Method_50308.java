/** 
 * Concatenates a filename to a base path using normal command line style rules. <p> The effect is equivalent to resultant directory after changing directory to the first argument, followed by changing directory to the second argument. <p> The first argument is the base path, the second is the path to concatenate. The returned path is always normalized via  {@link #normalize(String)}, thus <code>..</code> is handled. <p> If <code>pathToAdd</code> is absolute (has an absolute prefix), then it will be normalized and returned. Otherwise, the paths will be joined, normalized and returned. <p> The output will be the same on both Unix and Windows except for the separator character. <pre> /foo/ + bar          -->   /foo/bar /foo + bar           -->   /foo/bar /foo + /bar          -->   /bar /foo + C:/bar        -->   C:/bar /foo + C:bar         -->   C:bar (*) /foo/a/ + ../bar     -->   foo/bar /foo/ + ../../bar    -->   null /foo/ + /bar         -->   /bar /foo/.. + /bar       -->   /bar /foo + bar/c.txt     -->   /foo/bar/c.txt /foo/c.txt + bar     -->   /foo/c.txt/bar (!) </pre> (*) Note that the Windows relative drive prefix is unreliable when used with this method. (!) Note that the first parameter must be a path. If it ends with a name, then the name will be built into the concatenated path. If this might be a problem, use  {@link #getFullPath(String)} on the base path argument.
 * @param basePath          the base path to attach to, always treated as a path
 * @param fullFilenameToAdd the filename (or path) to attach to the base
 * @return the concatenated path, or null if invalid
 */
public static String concat(String basePath,String fullFilenameToAdd){
  int prefix=getPrefixLength(fullFilenameToAdd);
  if (prefix < 0) {
    return null;
  }
  if (prefix > 0) {
    return normalize(fullFilenameToAdd);
  }
  if (basePath == null) {
    return null;
  }
  int len=basePath.length();
  if (len == 0) {
    return normalize(fullFilenameToAdd);
  }
  char ch=basePath.charAt(len - 1);
  if (isSeparator(ch)) {
    return normalize(basePath + fullFilenameToAdd);
  }
 else {
    return normalize(basePath + '/' + fullFilenameToAdd);
  }
}
