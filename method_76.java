@Override public void _XXXXX_(String dedupIntervalMin,Map<String,Object> pluginProperties){
  super.update(dedupIntervalMin,pluginProperties);
  if (pluginProperties != null && !emailConfig.equals(pluginProperties)) {
    emailConfig=new HashMap<>(pluginProperties);
    emailGenerator=createEmailGenerator(pluginProperties);
  }
}