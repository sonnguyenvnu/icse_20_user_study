/** 
 * @param file the in-workspace base resource, if one exists
 * @param baseLocation - the location of the resource that contains the uri
 * @param publicId - an optional public identifier (i.e. namespace name), or null if none
 * @param systemId - an absolute or relative URI, or null if none
 * @return an absolute URI or null if this extension can not resolve this reference
 */
@Override public String resolve(IFile file,String baseLocation,String publicId,String systemId){
  if (null != systemId) {
    try {
      URI proposalByPreviousResolver=org.eclipse.emf.common.util.URI.createURI(systemId);
      String host=proposalByPreviousResolver.host();
      if (!(null == host || host.isEmpty())) {
        return REFUSE_EXTERNAL_URI;
      }
    }
 catch (    IllegalArgumentException ignore) {
    }
  }
  return null;
}
