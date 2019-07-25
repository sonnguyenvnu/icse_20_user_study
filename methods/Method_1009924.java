@Override public void register(@NonNull IComponentHostApplication moduleApp){
  moduleApplicationMap.put(moduleApp.getHost(),moduleApp);
  moduleApp.onCreate(Component.getApplication());
}
