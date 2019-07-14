@ConditionalOnMissingBean @Bean public OSS ossClient(AliCloudProperties aliCloudProperties,OssProperties ossProperties){
  if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.AK_SK) {
    Assert.isTrue(!StringUtils.isEmpty(ossProperties.getEndpoint()),"Oss endpoint can't be empty.");
    Assert.isTrue(!StringUtils.isEmpty(aliCloudProperties.getAccessKey()),"${spring.cloud.alicloud.access-key} can't be empty.");
    Assert.isTrue(!StringUtils.isEmpty(aliCloudProperties.getSecretKey()),"${spring.cloud.alicloud.secret-key} can't be empty.");
    return new OSSClientBuilder().build(ossProperties.getEndpoint(),aliCloudProperties.getAccessKey(),aliCloudProperties.getSecretKey(),ossProperties.getConfig());
  }
 else   if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.STS) {
    Assert.isTrue(!StringUtils.isEmpty(ossProperties.getEndpoint()),"Oss endpoint can't be empty.");
    Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getAccessKey()),"Access key can't be empty.");
    Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getSecretKey()),"Secret key can't be empty.");
    Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getSecurityToken()),"Security Token can't be empty.");
    return new OSSClientBuilder().build(ossProperties.getEndpoint(),ossProperties.getSts().getAccessKey(),ossProperties.getSts().getSecretKey(),ossProperties.getSts().getSecurityToken(),ossProperties.getConfig());
  }
 else {
    throw new IllegalArgumentException("Unknown auth mode.");
  }
}
