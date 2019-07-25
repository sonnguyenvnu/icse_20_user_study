/** 
 * Get mime type from header field content-type stripps any parameter (denoted by ';' see RFC 2616)
 * @return mime or on missing header field "application/octet-stream"
 */
public String mime(){
  final String tmpstr=this.get(CONTENT_TYPE,"application/octet-stream");
  final int pos=tmpstr.indexOf(';');
  if (pos > 0) {
    return tmpstr.substring(0,pos).trim();
  }
 else {
    return tmpstr;
  }
}
