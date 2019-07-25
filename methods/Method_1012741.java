@Override public GenericImageInfo copy(){
  return new GenericImageInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport);
}
