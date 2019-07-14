protected void setTag(int value,int type){
  if (type == TYPE_THUMB) {
    thumbTag=value;
  }
 else   if (type == TYPE_MEDIA) {
    mediaTag=value;
  }
 else {
    imageTag=value;
  }
}
