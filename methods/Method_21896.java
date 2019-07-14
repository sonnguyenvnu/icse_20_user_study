private void onRouteClicked(ResolveInfo route){
  ActivityInfo activity=route.activityInfo;
  ComponentName name=new ComponentName(activity.applicationInfo.packageName,activity.name);
  startActivity(new Intent(Intent.ACTION_VIEW).setComponent(name));
}
