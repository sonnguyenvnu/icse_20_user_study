/** 
 * {@inheritDoc}
 */
@Override public FileUpload create(final MultipartRequestInputStream input){
  return new AdaptiveFileUpload(input,memoryThreshold,uploadPath,maxFileSize,breakOnError,fileExtensions,allowFileExtensions);
}
