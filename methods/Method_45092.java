private static boolean isText(final String type){
  try {
    MediaType mediaType=MediaType.parse(type);
    return mediaType.is(MediaType.ANY_TEXT_TYPE) || mediaType.subtype().endsWith("javascript") || mediaType.subtype().endsWith("json") || mediaType.subtype().endsWith("xml") || mediaType.is(MediaType.FORM_DATA) || mediaType.subtype().endsWith("form-data");
  }
 catch (  Exception e) {
    return false;
  }
}
