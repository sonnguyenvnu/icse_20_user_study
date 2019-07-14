@Bean @ConditionalOnMissingBean @ConditionalOnClass(name="com.aliyuncs.edas.model.v20170801.GetSecureTokenRequest") public AliCloudEdasSdk aliCloudEdasSdk(AliCloudProperties aliCloudProperties,EdasProperties edasProperties){
  return AliCloudEdasSdkFactory.getDefaultAliCloudEdasSdk(aliCloudProperties,edasProperties.getRegionId());
}
