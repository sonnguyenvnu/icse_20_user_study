@Override public BMPInfo copy(){
  return new BMPInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport,compressionType);
}
