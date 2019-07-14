public static ImageLocation getForWebFile(WebFile webFile){
  if (webFile == null) {
    return null;
  }
  ImageLocation imageLocation=new ImageLocation();
  imageLocation.webFile=webFile;
  imageLocation.currentSize=webFile.size;
  return imageLocation;
}
