private static boolean isDeviceCompatibleWithConnectionServiceAPI(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)   return false;
  return "angler".equals(Build.PRODUCT) || "bullhead".equals(Build.PRODUCT) || "sailfish".equals(Build.PRODUCT) || "marlin".equals(Build.PRODUCT) || "walleye".equals(Build.PRODUCT) || "taimen".equals(Build.PRODUCT) || "blueline".equals(Build.PRODUCT) || "crosshatch".equals(Build.PRODUCT) || MessagesController.getGlobalMainSettings().getBoolean("dbg_force_connection_service",false);
}
