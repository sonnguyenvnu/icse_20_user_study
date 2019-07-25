/** 
 * Processes provided  {@link Request}.
 */
public ResourceMethodDescriptor process(final ServerResourceContext context){
  String path=context.getRequestURI().getRawPath();
  if (path.length() < 2) {
    throw new RoutingException(HttpStatus.S_404_NOT_FOUND.getCode());
  }
  if (path.charAt(0) == '/') {
    path=path.substring(1);
  }
  Queue<String> remainingPath=new LinkedList<>(Arrays.asList(SLASH_PATTERN.split(path)));
  String rootPath="/" + remainingPath.poll();
  ResourceModel currentResource;
  try {
    currentResource=_pathRootResourceMap.get(URLDecoder.decode(rootPath,RestConstants.DEFAULT_CHARSET_NAME));
  }
 catch (  UnsupportedEncodingException e) {
    throw new RestLiInternalException("UnsupportedEncodingException while trying to decode the root path",e);
  }
  if (currentResource == null) {
    throw new RoutingException(String.format("No root resource defined for path '%s'",rootPath),HttpStatus.S_404_NOT_FOUND.getCode());
  }
  return processResourceTree(currentResource,context,remainingPath);
}
