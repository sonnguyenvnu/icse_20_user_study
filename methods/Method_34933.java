public boolean isRegisteredDomain(String domain){
  if (onlineUpdate) {
    return publicSuffixList.isRegistrable(domain);
  }
 else {
    return InternetDomainName.from(domain).isTopPrivateDomain();
  }
}
