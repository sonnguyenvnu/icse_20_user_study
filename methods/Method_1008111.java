public Details parse(String agentString){
  Details details=cache.get(name,agentString);
  ;
  if (details == null) {
    VersionedName userAgent=findMatch(uaPatterns,agentString);
    VersionedName operatingSystem=findMatch(osPatterns,agentString);
    VersionedName device=findMatch(devicePatterns,agentString);
    details=new Details(userAgent,operatingSystem,device);
    cache.put(name,agentString,details);
  }
  return details;
}
