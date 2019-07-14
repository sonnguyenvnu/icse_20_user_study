public boolean contains(String domain){
  if (onlineUpdate) {
    return publicSuffixList.isPublicSuffix(domain);
  }
 else {
    return InternetDomainName.from(domain).isPublicSuffix();
  }
}
