public static int getMIUIMajorVersion(){
  String prop=AndroidUtilities.getSystemProperty("ro.miui.ui.version.name");
  if (prop != null) {
    try {
      return Integer.parseInt(prop.replace("V",""));
    }
 catch (    NumberFormatException ignore) {
    }
  }
  return -1;
}
