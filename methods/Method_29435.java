public static OS getDefaultOS(OSInfo osInfo){
  String uname=osInfo.getUname();
  if (uname == null) {
    return null;
  }
  uname=uname.toLowerCase();
  ProcessorArchitecture defaultArch=ProcessorArchitecture.X86_64;
  if (!uname.contains(defaultArch.getValue())) {
    defaultArch=ProcessorArchitecture.X86;
  }
  return new OS(OSType.LINUX,DistributionType.LINUX_OLD,DistributionVersion.DEFAULT,defaultArch);
}
