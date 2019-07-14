/** 
 * load spi.
 * @param hmilyConfig {@linkplain HmilyConfig}
 */
private void loadSpiSupport(final HmilyConfig hmilyConfig){
  final ObjectSerializer serializer=ExtensionLoader.getExtensionLoader(ObjectSerializer.class).getActivateExtension(hmilyConfig.getSerializer());
  final HmilyCoordinatorRepository repository=ExtensionLoader.getExtensionLoader(HmilyCoordinatorRepository.class).getActivateExtension(hmilyConfig.getRepositorySupport());
  repository.setSerializer(serializer);
  SpringBeanUtils.getInstance().registerBean(HmilyCoordinatorRepository.class.getName(),repository);
}
