private static void log(int priority,String message){
  if (configuration().isDebugEnabled()) {
    Log.println(priority,TAG,message);
  }
}
