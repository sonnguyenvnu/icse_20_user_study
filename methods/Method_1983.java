/** 
 * Adds imageFormat to the set of image formats you want to download. imageSize specify server-side resize options.
 */
public ImageUrlsRequestBuilder addImageFormat(ImageFormat imageFormat,ImageSize imageSize){
  mRequestedImageFormats.put(imageFormat,imageSize);
  return this;
}
