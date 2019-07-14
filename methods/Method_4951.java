/** 
 * Parses  {@link IcyHeaders} from response headers.
 * @param responseHeaders The response headers.
 * @return The parsed {@link IcyHeaders}, or  {@code null} if no ICY headers were present.
 */
@Nullable public static IcyHeaders parse(Map<String,List<String>> responseHeaders){
  boolean icyHeadersPresent=false;
  int bitrate=Format.NO_VALUE;
  String genre=null;
  String name=null;
  String url=null;
  boolean isPublic=false;
  int metadataInterval=C.LENGTH_UNSET;
  List<String> headers=responseHeaders.get(RESPONSE_HEADER_BITRATE);
  if (headers != null) {
    String bitrateHeader=headers.get(0);
    try {
      bitrate=Integer.parseInt(bitrateHeader) * 1000;
      if (bitrate > 0) {
        icyHeadersPresent=true;
      }
 else {
        Log.w(TAG,"Invalid bitrate: " + bitrateHeader);
        bitrate=Format.NO_VALUE;
      }
    }
 catch (    NumberFormatException e) {
      Log.w(TAG,"Invalid bitrate header: " + bitrateHeader);
    }
  }
  headers=responseHeaders.get(RESPONSE_HEADER_GENRE);
  if (headers != null) {
    genre=headers.get(0);
    icyHeadersPresent=true;
  }
  headers=responseHeaders.get(RESPONSE_HEADER_NAME);
  if (headers != null) {
    name=headers.get(0);
    icyHeadersPresent=true;
  }
  headers=responseHeaders.get(RESPONSE_HEADER_URL);
  if (headers != null) {
    url=headers.get(0);
    icyHeadersPresent=true;
  }
  headers=responseHeaders.get(RESPONSE_HEADER_PUB);
  if (headers != null) {
    isPublic=headers.get(0).equals("1");
    icyHeadersPresent=true;
  }
  headers=responseHeaders.get(RESPONSE_HEADER_METADATA_INTERVAL);
  if (headers != null) {
    String metadataIntervalHeader=headers.get(0);
    try {
      metadataInterval=Integer.parseInt(metadataIntervalHeader);
      if (metadataInterval > 0) {
        icyHeadersPresent=true;
      }
 else {
        Log.w(TAG,"Invalid metadata interval: " + metadataIntervalHeader);
        metadataInterval=C.LENGTH_UNSET;
      }
    }
 catch (    NumberFormatException e) {
      Log.w(TAG,"Invalid metadata interval: " + metadataIntervalHeader);
    }
  }
  return icyHeadersPresent ? new IcyHeaders(bitrate,genre,name,url,isPublic,metadataInterval) : null;
}
