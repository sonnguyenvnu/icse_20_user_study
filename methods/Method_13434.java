private static List<MediaType> toMediaTypes(Collection<String> mediaTypeValues){
  if (mediaTypeValues.isEmpty()) {
    return Collections.singletonList(MediaType.ALL);
  }
  return parseMediaTypes(new LinkedList<>(mediaTypeValues));
}
