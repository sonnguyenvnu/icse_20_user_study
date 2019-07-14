@Override public String requestCustomSizeUrl(Integer width,Integer height){
  if (baseImageUrl != null) {
    return baseImageUrl.buildUpon().appendQueryParameter("width",width.toString()).appendQueryParameter("height",height.toString()).build().toString();
  }
  throw new RuntimeException("You have to set the base image url first");
}
