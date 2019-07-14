@Override public void deleteConnection(OpenMode service){
  cloudHandler.clear(service);
  dataUtils.removeAccount(service);
  runOnUiThread(drawer::refreshDrawer);
}
