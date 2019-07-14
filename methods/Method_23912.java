public void setIcon(PImage icon){
  int w=icon.pixelWidth;
  int h=icon.pixelHeight;
  WritableImage im=new WritableImage(w,h);
  im.getPixelWriter().setPixels(0,0,w,h,PixelFormat.getIntArgbInstance(),icon.pixels,0,w);
  Stage stage=(Stage)canvas.getScene().getWindow();
  stage.getIcons().clear();
  stage.getIcons().add(im);
}
