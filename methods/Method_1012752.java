@Override public TIFFInfo copy(){
  return new TIFFInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,imageIOSupport,photometricInterpretation,exifOrientation,originalExifOrientation,exifVersion,exifCompression,exifColorSpace,hasExifThumbnail);
}
