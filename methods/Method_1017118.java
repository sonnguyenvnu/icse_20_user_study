public static EnvironmentMetadata create(){
  final String instanceId=MetadataConfig.getInstanceId();
  if (instanceId != null) {
    final String project=MetadataConfig.getProjectId();
    final String zone=MetadataConfig.getZone();
    final String region=zone.substring(0,zone.lastIndexOf("-"));
    final String role=MetadataConfig.getAttribute("instance/attributes/role");
    final String name=MetadataConfig.getAttribute("instance/name");
    return new AutoValue_EnvironmentMetadata(Optional.of(zone),Optional.of(instanceId),Optional.of(project),Optional.of(region),Optional.ofNullable(role),Optional.ofNullable(name));
  }
 else {
    return new AutoValue_EnvironmentMetadata(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
  }
}
