public static String formatStringSimple(String string,Object... args){
  try {
    if (getInstance().currentLocale != null) {
      return String.format(getInstance().currentLocale,string,args);
    }
 else {
      return String.format(string,args);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return "LOC_ERR: " + string;
  }
}
