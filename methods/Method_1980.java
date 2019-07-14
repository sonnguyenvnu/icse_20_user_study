private static List<String> getImageUrls(ImageUrlsRequest request){
  List<String> urls=new ArrayList<String>();
  try {
    String rawJson=downloadContentAsString(request.getEndpointUrl());
    if (rawJson == null) {
      return urls;
    }
    JSONObject json=new JSONObject(rawJson);
    JSONArray data=json.getJSONArray("data");
    for (int i=0; i < data.length(); i++) {
      JSONObject item=data.getJSONObject(i);
      if (!item.has("type")) {
        continue;
      }
      ImageFormat imageFormat=ImageFormat.getImageFormatForMime(item.getString("type"));
      ImageSize imageSize=request.getImageSize(imageFormat);
      if (imageSize != null) {
        urls.add(getThumbnailLink(item,imageSize));
      }
    }
  }
 catch (  Exception e) {
    FLog.e(TAG,"Exception fetching album",e);
  }
  return urls;
}
