@Override public GIFInfo copy(){
  return new GIFInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport,formatVersion,hasTransparency);
}
