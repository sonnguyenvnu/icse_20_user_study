/** 
 * Applicable only to HttpRequest methods extending HttpEntityEnclosingRequestBase, which is for example not DELETE
 * @param entity      entity to be included within the request
 * @param requestBase HttpRequest instance, must not be null
 */
private HttpEntityEnclosingRequestBase addEntityToRequestBase(HttpEntityEnclosingRequestBase requestBase,HttpEntity entity){
  if (entity != null) {
    requestBase.setEntity(entity);
  }
  return requestBase;
}
