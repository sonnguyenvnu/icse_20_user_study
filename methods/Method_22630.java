@Override public boolean openFolderAvailable(){
  if (Preferences.get("launcher") != null) {
    return true;
  }
  try {
    Process p=Runtime.getRuntime().exec(new String[]{"xdg-open"});
    p.waitFor();
    Preferences.set("launcher","xdg-open");
    return true;
  }
 catch (  Exception e) {
  }
  try {
    Process p=Runtime.getRuntime().exec(new String[]{"gnome-open"});
    p.waitFor();
    Preferences.set("launcher","gnome-open");
    return true;
  }
 catch (  Exception e) {
  }
  try {
    Process p=Runtime.getRuntime().exec(new String[]{"kde-open"});
    p.waitFor();
    Preferences.set("launcher","kde-open");
    return true;
  }
 catch (  Exception e) {
  }
  return false;
}
