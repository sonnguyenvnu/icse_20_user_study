/** 
 * Converts this endpoint into the authority part of a URI.
 * @return the authority string
 */
public String authority(){
  String authority=this.authority;
  if (authority != null) {
    return authority;
  }
  if (isGroup()) {
    authority="group:" + groupName;
  }
 else   if (port != 0) {
    if (hostType == HostType.IPv6_ONLY) {
      authority='[' + host() + "]:" + port;
    }
 else {
      authority=host() + ':' + port;
    }
  }
 else   if (hostType == HostType.IPv6_ONLY) {
    authority='[' + host() + ']';
  }
 else {
    authority=host();
  }
  return this.authority=authority;
}
