/** 
 * We get the size from a WebP image
 */
private Pair<Integer,Integer> readWebPImageSize(){
  final Pair<Integer,Integer> dimensions=WebpUtil.getSize(getInputStream());
  if (dimensions != null) {
    mWidth=dimensions.first;
    mHeight=dimensions.second;
  }
  return dimensions;
}
