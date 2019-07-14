private static boolean isMiUi(Resources resources){
  return resources.getClass().getName().equals("android.content.res.MiuiResources");
}
