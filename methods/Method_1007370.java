private void init(ClassPool cp){
  notDefinedHere=new HashMap<String,ClassLoader>();
  notDefinedPackages=new Vector<String>();
  source=cp;
  translator=null;
  domain=null;
  delegateLoadingOf("org.hotswap.agent.javassist.Loader");
}
