public SystemVersion build(){
  SystemVersion systemVersion=new SystemVersion();
  systemVersion.setName(name);
  systemVersion.setComment(comment);
  systemVersion.setWebsite(website);
  systemVersion.setVersion(version);
  return systemVersion;
}
