/** 
 * @see RequestManager#downloadOnly()
 */
@GlideType(File.class) public static void downloadOnlyDefaultPriority(RequestBuilder<File> requestBuilder){
  requestBuilder.apply(REQUEST_OPTIONS_DOWNLOAD_ONLY_DEFAULT_PRIORITY);
}
