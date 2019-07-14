private ImageDecoder createRegionDecoder(int left,int top,int right,int bottom){
  return new RegionDecoder(Fresco.getImagePipelineFactory().getPlatformDecoder(),new Rect(left,top,right,bottom));
}
