public void generator() throws Exception {
  List<String> warnings=new ArrayList<String>();
  boolean overwrite=true;
  File configFile=new File("generatorConfig.xml");
  ConfigurationParser cp=new ConfigurationParser(warnings);
  Configuration config=cp.parseConfiguration(configFile);
  DefaultShellCallback callback=new DefaultShellCallback(overwrite);
  MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
  myBatisGenerator.generate(null);
}
