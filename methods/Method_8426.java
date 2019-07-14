public void setImage(String path,String filter,String thumbPath,String thumbFilter){
  setImage(ImageLocation.getForPath(path),filter,ImageLocation.getForPath(thumbPath),thumbFilter,null,null,null,0,null);
}
