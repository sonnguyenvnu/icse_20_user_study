@Override public void dispose(){
  myFacade.setModelIdFactory(LanguageID.JAVA,null);
  myFacade.setModelRootFactory(JavaClassStubConstants.STUB_TYPE,null);
}
