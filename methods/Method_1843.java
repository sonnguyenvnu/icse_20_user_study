/** 
 * We get the size from a generic image 
 */
private ImageMetaData readImageMetaData(){
  InputStream inputStream=null;
  ImageMetaData metaData=null;
  try {
    inputStream=getInputStream();
    metaData=BitmapUtil.decodeDimensionsAndColorSpace(inputStream);
    mColorSpace=metaData.getColorSpace();
    Pair<Integer,Integer> dimensions=metaData.getDimensions();
    if (dimensions != null) {
      mWidth=dimensions.first;
      mHeight=dimensions.second;
    }
  }
  finally {
    if (inputStream != null) {
      try {
        inputStream.close();
      }
 catch (      IOException e) {
      }
    }
  }
  return metaData;
}
