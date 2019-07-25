public static void refresh(List<Config> list){
  Map<String,Object> map=Configs.getMap(null);
  if (map == null)   return;
  for (  Config config : list) {
    if (config == null || config.getKeyX() == null)     continue;
    map.put(config.getKeyX(),config.getValue());
  }
}
