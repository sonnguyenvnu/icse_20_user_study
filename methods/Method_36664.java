public void setVirtualViewTemplateAsync(String type,byte[] data){
  ViewManager viewManager=getService(ViewManager.class);
  viewManager.loadBinBufferAsync(type,data);
}
