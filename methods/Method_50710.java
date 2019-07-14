protected void init(){
  checkMembers=getProperty(CHECK_MEMBERS_DESCRIPTOR);
  checkLocals=getProperty(CHECK_LOCALS_DESCRIPTOR);
  checkParameters=getProperty(CHECK_PARAMETERS_DESCRIPTOR);
  staticPrefixes=getProperty(STATIC_PREFIXES_DESCRIPTOR);
  staticSuffixes=getProperty(STATIC_SUFFIXES_DESCRIPTOR);
  memberPrefixes=getProperty(MEMBER_PREFIXES_DESCRIPTOR);
  memberSuffixes=getProperty(MEMBER_SUFFIXES_DESCRIPTOR);
  localPrefixes=getProperty(LOCAL_PREFIXES_DESCRIPTOR);
  localSuffixes=getProperty(LOCAL_SUFFIXES_DESCRIPTOR);
  parameterPrefixes=getProperty(PARAMETER_PREFIXES_DESCRIPTOR);
  parameterSuffixes=getProperty(PARAMETER_SUFFIXES_DESCRIPTOR);
}
