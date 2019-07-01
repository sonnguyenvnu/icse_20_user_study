private boolean _XXXXX_(JobConfig config){
  return config.getConfig().get("spark.master").equalsIgnoreCase("yarn-client");
}