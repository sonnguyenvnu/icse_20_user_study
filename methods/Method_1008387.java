/** 
 * Upgrades the format of the keystore, if necessary. 
 */
public static void upgrade(KeyStoreWrapper wrapper,Path configDir) throws Exception {
  if (wrapper.getSettingNames().contains(SEED_SETTING.getKey())) {
    return;
  }
  addBootstrapSeed(wrapper);
  wrapper.save(configDir);
}
