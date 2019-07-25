public static HadoopUserProvider create(Settings settings){
  @SuppressWarnings("deprecation") HadoopUserProvider provider=new HadoopUserProvider();
  provider.setSettings(settings);
  return provider;
}
