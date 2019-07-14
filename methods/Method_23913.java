protected void setProcessingIcon(Stage stage){
  try {
    if (iconImages == null) {
      iconImages=new ArrayList<>();
      final int[] sizes={48,64,128,256,512};
      for (      int sz : sizes) {
        URL url=PApplet.class.getResource("/icon/icon-" + sz + ".png");
        Image image=new Image(url.toString());
        iconImages.add(image);
      }
    }
    List<Image> icons=stage.getIcons();
    icons.clear();
    icons.addAll(iconImages);
  }
 catch (  Exception e) {
  }
}
