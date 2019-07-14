/** 
 * Returns the media types that can be produced: <ul> <li>The producible media types specified in the request mappings, or <li>Media types of configured converters that can write the specific return value, or <li> {@link MediaType#ALL}</ul>
 * @param restMethodMetadata {@link RestMethodMetadata} from server side
 * @param returnValueClass   the class of return value
 * @return non-null {@link List}
 */
private List<MediaType> getProducibleMediaTypes(RestMethodMetadata restMethodMetadata,Class<?> returnValueClass){
  RequestMetadata serverRequestMetadata=restMethodMetadata.getRequest();
  List<MediaType> mediaTypes=serverRequestMetadata.getProduceMediaTypes();
  if (!CollectionUtils.isEmpty(mediaTypes)) {
    return mediaTypes;
  }
 else   if (!this.allSupportedMediaTypes.isEmpty()) {
    List<MediaType> result=new ArrayList<>();
    for (    HttpMessageConverter<?> converter : this.messageConverters) {
      if (converter.canWrite(returnValueClass,null)) {
        result.addAll(converter.getSupportedMediaTypes());
      }
    }
    return result;
  }
 else {
    return Collections.singletonList(MediaType.ALL);
  }
}
