public static ImageLocation getForPath(String path){
  if (path == null) {
    return null;
  }
  ImageLocation imageLocation=new ImageLocation();
  imageLocation.path=path;
  return imageLocation;
}
