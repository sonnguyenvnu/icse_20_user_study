private void loadBase64Image(){
  String[] parts=this.imagePath.split(";base64,");
  String extension=parts[0].substring(11);
  String encodedData=parts[1];
  byte[] decodedBytes=DatatypeConverter.parseBase64Binary(encodedData);
  if (decodedBytes == null) {
    System.err.println("Decode Error on image: " + imagePath.substring(0,20));
    return;
  }
  Image awtImage=new ImageIcon(decodedBytes).getImage();
  if (awtImage instanceof BufferedImage) {
    BufferedImage buffImage=(BufferedImage)awtImage;
    int space=buffImage.getColorModel().getColorSpace().getType();
    if (space == ColorSpace.TYPE_CMYK) {
      return;
    }
  }
  PImage loadedImage=new PImage(awtImage);
  if (loadedImage.width == -1) {
  }
  if (extension.equals("gif") || extension.equals("png") || extension.equals("unknown")) {
    loadedImage.checkAlpha();
  }
  setTexture(loadedImage);
}
