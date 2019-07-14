@Override protected void onMount(ComponentContext c,Object convertContent){
  final ComponentHost host=(ComponentHost)convertContent;
  if (Build.VERSION.SDK_INT >= 11) {
    host.setAlpha(1.0f);
  }
}
