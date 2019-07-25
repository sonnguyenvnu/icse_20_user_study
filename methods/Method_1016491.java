/** 
 * delete files and directories if a directory is not empty, delete also everything inside because deletion sometimes fails on windows, there is also a windows exec included
 * @param path
 */
public static void deletedelete(final File path){
  if (path == null || !path.exists()) {
    return;
  }
  if (path.isDirectory()) {
    final String[] list=path.list();
    if (list != null) {
      for (      final String s : list) {
        deletedelete(new File(path,s));
      }
    }
  }
  if (path.exists())   path.delete();
  if (path.exists()) {
    path.deleteOnExit();
    String p="";
    try {
      p=path.getCanonicalPath();
    }
 catch (    final IOException e1) {
    }
    if (System.getProperties().getProperty("os.name","").toLowerCase().startsWith("windows")) {
      try {
        final String command="cmd /C del /F /Q \"" + p + "\"";
        final Process r=Runtime.getRuntime().exec(command);
        if (r != null) {
          read(r.getInputStream());
        }
      }
 catch (      final IOException e) {
      }
    }
    if (path.exists()) {
    }
  }
}
