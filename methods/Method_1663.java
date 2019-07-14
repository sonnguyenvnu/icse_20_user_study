private static int getResourceId(ImageRequest imageRequest){
  return Integer.parseInt(imageRequest.getSourceUri().getPath().substring(1));
}
