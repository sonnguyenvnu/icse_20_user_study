public void update(String path){
  if (path == null) {
    resources=context.getResources();
    packageName=context.getPackageName();
    isLocal=true;
    refreshSkin();
  }
 else {
    updateSkin(path,exception -> {
    }
);
  }
}
