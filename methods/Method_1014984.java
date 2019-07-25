public static void show(Throwable e){
  CrashModel model=Utils.parseCrash(e);
  handleException(model);
}
