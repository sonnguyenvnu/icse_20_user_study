@Override public void notify(MediaEventListener listener){
  Picture picture=new Picture(thumbnail);
  listener.mediaThumbnailGenerated(component,picture);
}
