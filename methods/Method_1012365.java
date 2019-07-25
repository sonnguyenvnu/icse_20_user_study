private void update(Activity activity,AdaptInfo adaptInfo){
  DisplayMetrics AppDisplayMetrics=activity.getApplication().getResources().getDisplayMetrics();
  AppDisplayMetrics.density=adaptInfo.density;
  AppDisplayMetrics.densityDpi=adaptInfo.densityDpi;
  AppDisplayMetrics.scaledDensity=adaptInfo.scaledDensity;
  DisplayMetrics activityMetrics=activity.getResources().getDisplayMetrics();
  activityMetrics.scaledDensity=adaptInfo.scaledDensity;
  activityMetrics.densityDpi=adaptInfo.densityDpi;
  activityMetrics.density=adaptInfo.density;
  List<Resources> list=new ArrayList<>(2);
  list.add(activity.getApplication().getResources());
  list.add(activity.getResources());
  for (  Resources resource : list) {
    if ("MiuiResources".equals(resource.getClass().getSimpleName()) || "XResources".equals(resource.getClass().getSimpleName())) {
      try {
        Field field=Resources.class.getDeclaredField("mTmpMetrics");
        field.setAccessible(true);
        DisplayMetrics miDisplayMetrics=(DisplayMetrics)field.get(resource);
        miDisplayMetrics.scaledDensity=adaptInfo.scaledDensity;
        miDisplayMetrics.densityDpi=adaptInfo.densityDpi;
        miDisplayMetrics.density=adaptInfo.density;
      }
 catch (      Exception ignored) {
      }
    }
  }
}
