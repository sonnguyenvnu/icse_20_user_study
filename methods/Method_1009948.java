public static PermissionsUtil with(Context context){
  Activity rawAct=Utils.getActivityFromContext(context);
  if (rawAct != null) {
    return new PermissionsUtil(rawAct);
  }
 else {
    throw new IllegalArgumentException("PermissionsUtil.with -> parameter context not an Activity or this context not attach to Activity");
  }
}
