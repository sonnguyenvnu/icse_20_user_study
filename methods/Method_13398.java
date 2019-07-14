private boolean matchMediaType(List<MediaType> acceptedMediaTypes){
  for (  MediaType acceptedMediaType : acceptedMediaTypes) {
    if (getMediaType().isCompatibleWith(acceptedMediaType)) {
      return true;
    }
  }
  return false;
}
