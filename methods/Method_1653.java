private static String getAssetName(ImageRequest imageRequest){
  return imageRequest.getSourceUri().getPath().substring(1);
}
