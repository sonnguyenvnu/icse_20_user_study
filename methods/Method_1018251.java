/** 
 * do the request uri process 
 */
private void process(){
  if (requestUri.length() > 1) {
    parts=new ArrayList<String>(10);
    for (int i=1; i < requestUri.length(); ) {
      int sIdx=i;
      int eIdx=requestUri.indexOf('/',sIdx + 1);
      if (eIdx == -1) {
        parts.add(requestUri.substring(sIdx));
        break;
      }
      parts.add(requestUri.substring(sIdx,eIdx));
      i=eIdx + 1;
    }
    if (requestUri.charAt(requestUri.length() - 1) == '/') {
      parts.add("");
    }
    int length=parts.size();
    if (length > 1) {
      IStringBuffer sb=new IStringBuffer();
      for (int i=0; i < length - 1; i++) {
        int l=sb.length();
        sb.append(parts.get(i));
        char chr=sb.charAt(l);
        if (chr >= 90) {
          chr-=32;
          sb.set(l,chr);
        }
      }
      controller=sb.toString();
    }
    method=parts.get(length - 1);
  }
}
