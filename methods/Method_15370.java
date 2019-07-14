public Map read(){
  InputStream dumpFile=this.getClass().getClassLoader().getResourceAsStream("application.yml");
  Map father=null;
  try {
    father=Yaml.loadType(dumpFile,HashMap.class);
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
  }
  return father;
}
