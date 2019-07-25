/** 
 * Process  {@code Modified} according to given the supplied {@code Last-Modified} and {@code ETag}.
 * @param eTag the tag of the resource.
 * @param lastModified he last-modified timestamp in milliseconds of the resource.
 * @return true if the request does not require further processing.
 */
public boolean process(@Nullable String eTag,long lastModified){
  if (isNotModified)   return true;
  if (validateIfUnmodifiedSince(lastModified)) {
    if (!isNotModified) {
      mResponse.setStatus(StatusCode.SC_LENGTH_REQUIRED);
    }
    return isNotModified;
  }
  boolean validated=validateIfNoneMatch(eTag);
  if (!validated)   validateIfModifiedSince(lastModified);
  HttpMethod method=mRequest.getMethod();
  boolean isGetHead=(method == HttpMethod.GET || method == HttpMethod.HEAD);
  if (isNotModified) {
    mResponse.setStatus(isGetHead ? StatusCode.SC_NOT_MODIFIED : StatusCode.SC_LENGTH_REQUIRED);
  }
  if (isGetHead) {
    if (lastModified > 0 && mResponse.getHeader(LAST_MODIFIED) == null) {
      mResponse.setDateHeader(LAST_MODIFIED,lastModified);
    }
    if (StringUtils.hasLength(eTag) && mResponse.getHeader(ETAG) == null) {
      mResponse.setHeader(ETAG,padETagIfNecessary(eTag));
    }
    mResponse.setHeader(CACHE_CONTROL,"private");
  }
  return isNotModified;
}
