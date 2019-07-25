@Override public void init(){
  myFacade.setModelRootFactory(JavaClassStubConstants.STUB_TYPE,new JavaClassStubModelRootFactory());
  myFacade.setModelIdFactory(LanguageID.JAVA,new JavaPackageModelId.Factory());
}
