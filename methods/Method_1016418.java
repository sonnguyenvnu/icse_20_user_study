/** 
 * Override legacy mime()
 * @return mime string or "application/octet-stream" if content type missing
 * @see getContentType()
 */
@Override public String mime(){
  if (super.containsKey(HeaderFramework.CONTENT_TYPE)) {
    return super.mime();
  }
 else {
    if (_request != null) {
      return _request.getContentType();
    }
  }
  return "application/octet-stream";
}
