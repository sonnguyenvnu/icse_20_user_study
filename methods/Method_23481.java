private void loadFileSystemImage(PGraphics g){
  imagePath=imagePath.substring(7);
  PImage loadedImage=g.parent.loadImage(imagePath);
  if (loadedImage == null) {
    System.err.println("Error loading image file: " + imagePath);
  }
 else {
    setTexture(loadedImage);
  }
}
