@Nullable public static Rule compose(AndroidAppTarget target){
  Keystore keystore=target.getKeystore();
  if (keystore != null) {
    return new KeystoreRule().storeFile(Preconditions.checkNotNull(fileRule(keystore.getStoreFile()))).storePassword(keystore.getStorePassword()).keyAlias(keystore.getKeyAlias()).keyPassword(keystore.getKeyPassword()).ruleType(RuleType.KEYSTORE.getBuckName()).name(keystore(target));
  }
 else {
    throw new IllegalStateException(target.getName() + " of " + target.getPath() + " has no signing config set!");
  }
}
