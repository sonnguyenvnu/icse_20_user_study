@Override public PNGInfo copy(){
  return new PNGInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport,colorType,interlaceMethod,hasTransparencyChunk,isModifiedBitDepth);
}
