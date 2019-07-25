@Override public boolean exists(Settings settings){
  final SecureSettings secureSettings=settings.getSecureSettings();
  return secureSettings != null && secureSettings.getSettingNames().contains(getKey());
}
