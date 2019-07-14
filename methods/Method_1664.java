private static int calculateKind(ImageRequest imageRequest){
  if (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) {
    return MediaStore.Images.Thumbnails.MINI_KIND;
  }
  return MediaStore.Images.Thumbnails.MICRO_KIND;
}
