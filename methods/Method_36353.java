@Override public void loadSpringXMLs(){
  springResources=new HashMap<>();
  File springXml=new File(url.getFile().substring(0,url.getFile().length() - SOFA_MODULE_FILE.length()),SofaModuleFrameworkConstants.SPRING_CONTEXT_PATH);
  List<File> springFiles=new ArrayList<>();
  if (springXml.exists()) {
    listFiles(springFiles,springXml,".xml");
  }
  try {
    for (    File f : springFiles) {
      springResources.put(f.getAbsolutePath(),new FileSystemResource(f));
    }
  }
 catch (  Throwable e) {
    throw new RuntimeException(e);
  }
}
