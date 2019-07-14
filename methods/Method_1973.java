private void loadNetworkUrls(){
  String url="https://api.imgur.com/3/gallery/hot/viral/0.json";
  ImageSize staticSize=chooseImageSize();
  ImageUrlsRequestBuilder builder=new ImageUrlsRequestBuilder(url).addImageFormat(ImageFormat.JPEG,staticSize).addImageFormat(ImageFormat.PNG,staticSize);
  if (mAllowAnimations) {
    builder.addImageFormat(ImageFormat.GIF,ImageSize.ORIGINAL_IMAGE);
  }
  ImageUrlsFetcher.getImageUrls(builder.build(),new ImageUrlsFetcher.Callback(){
    @Override public void onFinish(    List<String> result){
      if (!mUrlsLocal) {
        mImageUrls=result;
        updateAdapter(mImageUrls);
      }
    }
  }
);
}
