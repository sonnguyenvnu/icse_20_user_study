public static void main(String... args){
  try {
    if (args.length < 2) {
      throw new IllegalArgumentException("Current source directory and build version required as arguments");
    }
    String resourcesDir=args[0];
    Resource versionsResource=new FileSystemResource(new File(resourcesDir,UPGRADE_VERSIONS_FILE));
    KyloVersion projectVersion=KyloVersionUtil.parseVersion(args[1]).withoutTag();
    List<KyloVersion> upgradeVersions=Stream.concat(readUpgradeVersions(versionsResource).stream(),Stream.of(projectVersion)).sorted().distinct().collect(Collectors.toList());
    writeUpgradeVersions(upgradeVersions,versionsResource.getFile());
  }
 catch (  Exception e) {
    throw new UpgradeException("Failed to update versions file: " + UPGRADE_VERSIONS_FILE,e);
  }
}
