@Override protected void onRemoveFromParent(){
  MediaController.getInstance().setTextureView(videoTextureView,null,null,false);
}
