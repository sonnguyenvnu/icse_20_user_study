/** 
 * <p> Transforms the input <code>source</code> by the according method defined in subclass to another string. It expects the transformation to be read from a file which is stored under the 'conf/transform'
 * @param filename the name of the file which contains the transformation definition.The name may contain subfoldernames as well
 * @param source the input to transform
 * @throws TransformationException
 */
@Override public @Nullable String transform(String filename,String source) throws TransformationException {
  if (filename == null || source == null) {
    throw new TransformationException("the given parameters 'filename' and 'source' must not be null");
  }
  final WatchService watchService=getWatchService();
  processFolderEvents(watchService);
  String transformFile=getLocalizedProposedFilename(filename,watchService);
  T transform=cachedFiles.get(transformFile);
  if (transform == null) {
    transform=internalLoadTransform(transformFile);
    cachedFiles.put(transformFile,transform);
  }
  try {
    return internalTransform(transform,source);
  }
 catch (  TransformationException e) {
    logger.warn("Could not transform '{}' with the file '{}' : {}",source,filename,e.getMessage());
    return "";
  }
}
