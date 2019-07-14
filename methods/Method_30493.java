public ApiRequest<Broadcast> sendBroadcast(String text,List<String> imageUrls,String linkTitle,String linkUrl){
  boolean isImagesEmpty=CollectionUtils.isEmpty(imageUrls);
  if (isImagesEmpty && !TextUtils.isEmpty(linkUrl)) {
    return new ConvertBroadcastApiRequest(mFrodoService.sendBroadcastWithLifeStream(text,null,linkTitle,linkUrl));
  }
  String imageUrlsString=!isImagesEmpty ? StringCompat.join(",",imageUrls) : null;
  return mFrodoService.sendBroadcast(text,imageUrlsString,linkTitle,linkUrl);
}
