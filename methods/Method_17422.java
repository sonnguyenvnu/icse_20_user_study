/** 
 * Returns the request's path or  {@code null} if this request should be ignored. The input isspace deliminated with the following format, <ul> <li>A monotonically increasing counter (useful for sorting the trace in chronological order) <li>The timestamp of the request in Unix notation with millisecond precision <li>The requested URL <li>A flag to indicate if the request resulted in a database update or not ('-' or 'save') </ul>
 */
private @Nullable String parseRequest(String line){
  if (!isRead(line)) {
    return null;
  }
  String url=getRequestUrl(line);
  if (url.length() > 12) {
    String path=getPath(url);
    if (isAllowed(path)) {
      return path;
    }
  }
  return null;
}
