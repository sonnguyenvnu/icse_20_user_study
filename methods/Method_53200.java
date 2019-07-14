@Override public Configuration getInstance(String configTreePath){
  PropertyConfiguration conf=new PropertyConfiguration(configTreePath);
  conf.dumpConfiguration();
  return conf;
}
