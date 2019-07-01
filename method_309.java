private void _XXXXX_(FileConfiguration fileConfig){
  Iterator fileIter=fileConfig.getKeys();
  while (fileIter.hasNext()) {
    String key=(String)fileIter.next();
    setViewProperty(fileConfig,key,fileConfig.getProperty(key));
  }
}