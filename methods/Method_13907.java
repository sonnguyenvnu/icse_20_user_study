public static String extractSpreadSheetId(String url) throws IllegalArgumentException {
  URL urlAsUrl;
  Matcher matcher=Pattern.compile("(?<=\\/d\\/).*(?=\\/.*)").matcher(url);
  if (matcher.find()) {
    return matcher.group(0);
  }
  try {
    urlAsUrl=new URL(url);
    String query=urlAsUrl.getQuery();
    if (query != null) {
      String[] parts=query.split("&");
      int offset=-1;
      int numParts=0;
      String keyOrId="";
      for (      String part : parts) {
        if (part.startsWith("id=")) {
          offset=("id=").length();
          keyOrId=part.substring(offset);
          numParts=4;
          break;
        }
 else         if (part.startsWith("key=")) {
          offset=("key=").length();
          keyOrId=part.substring(offset);
          if (!keyOrId.isEmpty()) {
            return keyOrId;
          }
          numParts=2;
          break;
        }
      }
      if (offset > -1) {
        String[] dottedParts=keyOrId.split("\\.");
        if (dottedParts.length == numParts) {
          return dottedParts[0] + "." + dottedParts[1];
        }
      }
    }
  }
 catch (  MalformedURLException e) {
    String[] dottedParts=url.split("\\.");
    if (dottedParts.length == 4 || dottedParts.length == 2) {
      return dottedParts[0] + "." + dottedParts[1];
    }
  }
  throw new IllegalArgumentException("Unknown URL format.");
}
