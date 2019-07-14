/** 
 * Returns the offset necessary to make sure that: - the image is centered within the limit if the image is smaller than the limit - there is no empty space on left/right if the image is bigger than the limit
 */
private float getOffset(float imageStart,float imageEnd,float limitStart,float limitEnd,float limitCenter){
  float imageWidth=imageEnd - imageStart, limitWidth=limitEnd - limitStart;
  float limitInnerWidth=Math.min(limitCenter - limitStart,limitEnd - limitCenter) * 2;
  if (imageWidth < limitInnerWidth) {
    return limitCenter - (imageEnd + imageStart) / 2;
  }
  if (imageWidth < limitWidth) {
    if (limitCenter < (limitStart + limitEnd) / 2) {
      return limitStart - imageStart;
    }
 else {
      return limitEnd - imageEnd;
    }
  }
  if (imageStart > limitStart) {
    return limitStart - imageStart;
  }
  if (imageEnd < limitEnd) {
    return limitEnd - imageEnd;
  }
  return 0;
}
