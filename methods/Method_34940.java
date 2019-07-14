public void setURL(String url){
  this.url=url;
  int domainStartIdx=url.indexOf("//") + 2;
  int domainEndIdx=url.indexOf('/',domainStartIdx);
  domainEndIdx=(domainEndIdx > domainStartIdx) ? domainEndIdx : url.length();
  String domain=url.substring(domainStartIdx,domainEndIdx);
  registeredDomain=domain;
  subDomain="";
  if (tldList != null && !(domain.isEmpty()) && InternetDomainName.isValid(domain)) {
    String candidate=null;
    String rd=null;
    String sd=null;
    String[] parts=domain.split("\\.");
    for (int i=parts.length - 1; i >= 0; i--) {
      if (rd == null) {
        if (candidate == null) {
          candidate=parts[i];
        }
 else {
          candidate=parts[i] + "." + candidate;
        }
        if (tldList.isRegisteredDomain(candidate)) {
          rd=candidate;
        }
      }
 else {
        if (sd == null) {
          sd=parts[i];
        }
 else {
          sd=parts[i] + "." + sd;
        }
      }
    }
    if (rd != null) {
      registeredDomain=rd;
    }
    if (sd != null) {
      subDomain=sd;
    }
  }
  path=url.substring(domainEndIdx);
  int pathEndIdx=path.indexOf('?');
  if (pathEndIdx >= 0) {
    path=path.substring(0,pathEndIdx);
  }
}
