private void init(){
  File jbf=new File(Utils.getRootClassPath(),"jboot.properties");
  if (!jbf.exists()) {
    mainProperties=new Properties();
  }
 else {
    mainProperties=new Prop("jboot.properties").getProperties();
  }
  String mode=getConfigValue("jboot.app.mode");
  if (Utils.isNotBlank(mode)) {
    String p=String.format("jboot-%s.properties",mode);
    if (new File(Utils.getRootClassPath(),p).exists()) {
      mainProperties.putAll(new Prop(p).getProperties());
    }
  }
}
