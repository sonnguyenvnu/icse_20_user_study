public Image loadImageX(String filename){
  final int res=Toolkit.highResImages() ? 2 : 1;
  return loadImage(filename + "-" + res + "x.png");
}
