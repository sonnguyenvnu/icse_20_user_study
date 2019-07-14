public static boolean isMainProcess(Context context){
  String processName=getProcessNameByPid(context,Process.myPid());
  if (context.getPackageName().equals(processName)) {
    return true;
  }
  return false;
}
