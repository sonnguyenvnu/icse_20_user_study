private static List<String> toMediaTypeValues(List<MediaType> mediaTypes){
  List<String> list=new ArrayList<>(mediaTypes.size());
  for (  MediaType mediaType : mediaTypes) {
    list.add(mediaType.toString());
  }
  return list;
}
