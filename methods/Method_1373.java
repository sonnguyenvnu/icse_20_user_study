/** 
 * Return a URI for the given resource ID. The returned URI consists of a  {@link #LOCAL_RESOURCE_SCHEME} scheme andthe resource ID as path.
 * @param resourceId the resource ID to use
 * @return the URI
 */
public static Uri getUriForResourceId(int resourceId){
  return new Uri.Builder().scheme(LOCAL_RESOURCE_SCHEME).path(String.valueOf(resourceId)).build();
}
