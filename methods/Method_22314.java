/** 
 * try to get the keystore
 * @param context a context
 * @param config the configuration
 * @return the keystore, or null if none provided / failure
 */
@Nullable public static KeyStore getKeyStore(@NonNull Context context,@NonNull CoreConfiguration config){
  final HttpSenderConfiguration senderConfiguration=ConfigUtils.getPluginConfiguration(config,HttpSenderConfiguration.class);
  final InstanceCreator instanceCreator=new InstanceCreator();
  KeyStore keyStore=instanceCreator.create(senderConfiguration.keyStoreFactoryClass(),NoKeyStoreFactory::new).create(context);
  if (keyStore == null) {
    final int certificateRes=senderConfiguration.resCertificate();
    final String certificatePath=senderConfiguration.certificatePath();
    final String certificateType=senderConfiguration.certificateType();
    if (certificateRes != ACRAConstants.DEFAULT_RES_VALUE) {
      keyStore=new ResourceKeyStoreFactory(certificateType,certificateRes).create(context);
    }
 else     if (!certificatePath.equals(ACRAConstants.DEFAULT_STRING_VALUE)) {
      if (certificatePath.startsWith(ASSET_PREFIX)) {
        keyStore=new AssetKeyStoreFactory(certificateType,certificatePath.substring(ASSET_PREFIX.length())).create(context);
      }
 else {
        keyStore=new FileKeyStoreFactory(certificateType,certificatePath).create(context);
      }
    }
  }
  return keyStore;
}
