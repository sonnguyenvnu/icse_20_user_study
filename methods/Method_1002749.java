private static SerializationFormat register(BiMap<String,SerializationFormat> uriTextToFormats,Multimap<MediaType,SerializationFormat> simplifiedMediaTypeToFormats,SerializationFormatProvider.Entry entry){
  checkState(!uriTextToFormats.containsKey(entry.uriText),"serialization format registered already: ",entry.uriText);
  final SerializationFormat value=new SerializationFormat(entry.uriText,entry.primaryMediaType,entry.mediaTypes);
  for (  MediaType type : entry.mediaTypes) {
    checkMediaType(simplifiedMediaTypeToFormats,type);
  }
  uriTextToFormats.put(entry.uriText,value);
  for (  MediaType type : entry.mediaTypes) {
    simplifiedMediaTypeToFormats.put(type.withoutParameters(),value);
  }
  return value;
}
