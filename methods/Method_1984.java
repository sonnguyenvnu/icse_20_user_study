public ImageUrlsRequest build(){
  ImageUrlsRequest request=new ImageUrlsRequest(mEndpointUrl,mRequestedImageFormats);
  mRequestedImageFormats=null;
  return request;
}
