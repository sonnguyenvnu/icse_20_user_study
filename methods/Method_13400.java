/** 
 * Resolve the most match  {@link HttpMessageConverter} from {@link RequestMetadata}
 * @param requestMetadata    {@link RequestMetadata}
 * @param restMethodMetadata {@link RestMethodMetadata}
 * @return
 */
public HttpMessageConverterHolder resolve(RequestMetadata requestMetadata,RestMethodMetadata restMethodMetadata){
  HttpMessageConverterHolder httpMessageConverterHolder=null;
  Class<?> returnValueClass=resolveReturnValueClass(restMethodMetadata);
  List<MediaType> requestedMediaTypes=getAcceptableMediaTypes(requestMetadata);
  List<MediaType> producibleMediaTypes=getProducibleMediaTypes(restMethodMetadata,returnValueClass);
  Set<MediaType> compatibleMediaTypes=new LinkedHashSet<MediaType>();
  for (  MediaType requestedType : requestedMediaTypes) {
    for (    MediaType producibleType : producibleMediaTypes) {
      if (requestedType.isCompatibleWith(producibleType)) {
        compatibleMediaTypes.add(getMostSpecificMediaType(requestedType,producibleType));
      }
    }
  }
  if (compatibleMediaTypes.isEmpty()) {
    return httpMessageConverterHolder;
  }
  List<MediaType> mediaTypes=new ArrayList<>(compatibleMediaTypes);
  MediaType.sortBySpecificityAndQuality(mediaTypes);
  MediaType selectedMediaType=null;
  for (  MediaType mediaType : mediaTypes) {
    if (mediaType.isConcrete()) {
      selectedMediaType=mediaType;
      break;
    }
 else     if (mediaType.equals(MediaType.ALL) || mediaType.equals(MEDIA_TYPE_APPLICATION)) {
      selectedMediaType=MediaType.APPLICATION_OCTET_STREAM;
      break;
    }
  }
  if (selectedMediaType != null) {
    selectedMediaType=selectedMediaType.removeQualityValue();
    for (    HttpMessageConverter<?> messageConverter : this.messageConverters) {
      if (messageConverter.canWrite(returnValueClass,selectedMediaType)) {
        httpMessageConverterHolder=new HttpMessageConverterHolder(selectedMediaType,messageConverter);
        break;
      }
    }
  }
  return httpMessageConverterHolder;
}
