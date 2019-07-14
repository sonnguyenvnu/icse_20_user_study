/** 
 * Whether toTest file is directory or not TODO: Avoid parsing ls
 */
public static boolean isDirectory(String toTest,boolean root,int count) throws ShellNotRunningException {
  File f=new File(toTest);
  String name=f.getName();
  String p=f.getParent();
  if (p != null && p.length() > 0) {
    ArrayList<String> ls=runShellCommandToList("ls -l " + p);
    for (    String s : ls) {
      if (contains(s.split(" "),name)) {
        try {
          HybridFileParcelable path=FileUtils.parseName(s);
          if (path.getPermission().trim().startsWith("d"))           return true;
 else           if (path.getPermission().trim().startsWith("l")) {
            if (count > 5)             return f.isDirectory();
 else             return isDirectory(path.getLink().trim(),root,++count);
          }
 else           return f.isDirectory();
        }
 catch (        Exception e) {
          e.printStackTrace();
        }
        break;
      }
    }
  }
  return f.isDirectory();
}
