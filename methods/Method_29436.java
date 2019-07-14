/** 
 * ??uname -a???/etc/issue??????????????
 * @param osInfo
 * @return OS
 */
public static OS getOS(OSInfo osInfo){
  String uname=osInfo.getUname();
  String issue=osInfo.getIssue();
  OSType osType=OSType.LINUX;
  ProcessorArchitecture defaultArch=ProcessorArchitecture.X86_64;
  DistributionType defaultDist=DistributionType.LINUX_OLD;
  DistributionVersion version=DistributionVersion.DEFAULT;
  if (uname == null || issue == null) {
    OS os=new OS(osType,defaultDist,version,defaultArch);
    return os;
  }
  uname=uname.toLowerCase();
  if (!uname.contains(OSType.LINUX.getValue())) {
    logger.error("os={} is temporarily not supported",uname);
    return null;
  }
  if (!uname.contains(defaultArch.getValue())) {
    defaultArch=ProcessorArchitecture.X86;
  }
  issue=issue.toLowerCase();
  DistributionType findType=DistributionType.findByContains(issue);
  if (findType == null) {
    logger.warn("dist cannot matched, {}",issue);
    OS os=new OS(osType,defaultDist,version,defaultArch);
    return os;
  }
  Matcher matcher=VERSION_PATTERN.matcher(issue);
  if (!matcher.find()) {
    logger.warn("version not matched, {}",issue);
    OS os=new OS(osType,defaultDist,version,defaultArch);
    return os;
  }
  String ver=matcher.group();
  ver=ver.replaceAll("\\.","");
  logger.info("version matched, {} - {}",ver,issue);
  DistributionVersion versionResult=findVersion(findType.getVersions(),ver);
  if (versionResult == null) {
    logger.info("version {} not found, {}",ver);
    OS os=new OS(osType,defaultDist,version,defaultArch);
    return os;
  }
  OS os=new OS(osType,findType,versionResult,defaultArch);
  logger.info("find OS={}",os);
  return os;
}
