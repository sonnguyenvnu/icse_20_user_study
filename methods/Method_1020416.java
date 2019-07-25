static Resource detect(){
  if (AwsIdentityDocUtils.isRunningOnAws()) {
    return create("","",AwsIdentityDocUtils.getInstanceId(),AwsIdentityDocUtils.getMachineType());
  }
  if (GcpMetadataConfig.isRunningOnGcp()) {
    return create(GcpMetadataConfig.getInstanceHostname(),GcpMetadataConfig.getInstanceName(),GcpMetadataConfig.getInstanceId(),GcpMetadataConfig.getMachineType());
  }
  return EMPTY_RESOURCE;
}
