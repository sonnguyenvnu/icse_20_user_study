/** 
 * Returns the length of the filename prefix, such as <code>C:/</code> or <code>~/</code>. <p> This method will handle a file in either Unix or Windows format. <p> The prefix length includes the first slash in the full filename if applicable. Thus, it is possible that the length returned is greater than the length of the input string. <pre> Windows: a\b\c.txt           --> ""          --> relative \a\b\c.txt          --> "\"         --> current drive absolute C:a\b\c.txt         --> "C:"        --> drive relative C:\a\b\c.txt        --> "C:\"       --> absolute \\server\a\b\c.txt  --> "\\server\" --> UNC Unix: a/b/c.txt           --> ""          --> relative /a/b/c.txt          --> "/"         --> absolute ~/a/b/c.txt         --> "~/"        --> current user ~                   --> "~/"        --> current user (slash added) ~user/a/b/c.txt     --> "~user/"    --> named user ~user               --> "~user/"    --> named user (slash added) </pre> <p> The output will be the same irrespective of the machine that the code is running on. ie. both Unix and Windows prefixes are matched regardless.
 * @param filename the filename to find the prefix in, null returns -1
 * @return the length of the prefix, -1 if invalid or null
 */
public static int getPrefixLength(String filename){
  if (filename == null) {
    return -1;
  }
  int len=filename.length();
  if (len == 0) {
    return 0;
  }
  char ch0=filename.charAt(0);
  if (ch0 == ':') {
    return -1;
  }
  if (len == 1) {
    if (ch0 == '~') {
      return 2;
    }
    return isSeparator(ch0) ? 1 : 0;
  }
 else {
    if (ch0 == '~') {
      int posUnix=filename.indexOf(UNIX_SEPARATOR,1);
      int posWin=filename.indexOf(WINDOWS_SEPARATOR,1);
      if (posUnix == -1 && posWin == -1) {
        return len + 1;
      }
      posUnix=posUnix == -1 ? posWin : posUnix;
      posWin=posWin == -1 ? posUnix : posWin;
      return Math.min(posUnix,posWin) + 1;
    }
    char ch1=filename.charAt(1);
    if (ch1 == ':') {
      ch0=Character.toUpperCase(ch0);
      if (ch0 >= 'A' && ch0 <= 'Z') {
        if (len == 2 || !isSeparator(filename.charAt(2))) {
          return 2;
        }
        return 3;
      }
      return -1;
    }
 else     if (isSeparator(ch0) && isSeparator(ch1)) {
      int posUnix=filename.indexOf(UNIX_SEPARATOR,2);
      int posWin=filename.indexOf(WINDOWS_SEPARATOR,2);
      if (posUnix == -1 && posWin == -1 || posUnix == 2 || posWin == 2) {
        return -1;
      }
      posUnix=posUnix == -1 ? posWin : posUnix;
      posWin=posWin == -1 ? posUnix : posWin;
      return Math.min(posUnix,posWin) + 1;
    }
 else {
      return isSeparator(ch0) ? 1 : 0;
    }
  }
}
