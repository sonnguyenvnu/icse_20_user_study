public static void check(Context context){
  File file=new File(Config.EMULATOR_DIR,"DATA_VERSION");
  int version=0;
  try {
    version=readVersion(file);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  if (version < CURRENT_VERSION) {
    try {
      moveConfigs(context);
      writeVersion(file,CURRENT_VERSION);
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
