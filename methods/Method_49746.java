static boolean isDebug(Context context){
  return (0 != (context.getApplicationContext().getApplicationInfo().flags&=ApplicationInfo.FLAG_DEBUGGABLE));
}
