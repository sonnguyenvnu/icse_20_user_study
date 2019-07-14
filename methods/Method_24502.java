public static boolean isSimulated(){
  return alwaysSimulate || !"Linux".equals(System.getProperty("os.name"));
}
