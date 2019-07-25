@Override public PCXInfo copy(){
  return new PCXInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport);
}
