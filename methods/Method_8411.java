public AnimatedFileDrawable makeCopy(){
  AnimatedFileDrawable drawable;
  if (stream != null) {
    drawable=new AnimatedFileDrawable(path,false,streamFileSize,stream.getDocument(),stream.getParentObject(),currentAccount);
  }
 else {
    drawable=new AnimatedFileDrawable(path,false,streamFileSize,null,null,currentAccount);
  }
  drawable.metaData[0]=metaData[0];
  drawable.metaData[1]=metaData[1];
  return drawable;
}
