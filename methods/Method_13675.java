private List<String> getComponents(){
  List<String> components=new ArrayList<>();
  if (scxContextAutoConfiguration != null && scxProperties != null) {
    components.add("SC-SCX");
  }
  if (ossContextAutoConfiguration != null && ossProperties != null) {
    components.add("SC-OSS");
  }
  boolean edasEnabled=edasProperties != null && edasProperties.isEnabled();
  boolean ansEnableEdas=edasEnabled || (ansProperties != null && ansProperties.getServerMode() == AliCloudServerMode.EDAS);
  if (ansContextAutoConfiguration != null && ansEnableEdas) {
    components.add("SC-ANS");
  }
  boolean acmEnableEdas=edasEnabled || (acmProperties != null && acmProperties.getServerMode() == AliCloudServerMode.EDAS);
  if (acmContextBootstrapConfiguration != null && acmEnableEdas) {
    components.add("SC-ACM");
  }
  if (NACOS_SERVER_MODE_VALUE.equals(System.getProperty(NACOS_CONFIG_SERVER_MODE_KEY))) {
    components.add("SC-NACOS-CONFIG");
  }
  if (NACOS_SERVER_MODE_VALUE.equals(System.getProperty(NACOS_DISCOVERY_SERVER_MODE_KEY))) {
    components.add("SC-NACOS-DISCOVERY");
  }
  return components;
}
