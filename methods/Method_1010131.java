public TransientModelWithMetainfo load(int majorStep,int minorStep,SModelReference modelReference){
  try {
    InputStream stream=myCacheContainer.openStream(getStorageName(majorStep,minorStep));
    ModelInputStream is=new ModelInputStream(stream);
    try {
      return TransientModelWithMetainfo.load(is,modelReference);
    }
  finally {
      is.close();
    }
  }
 catch (  IOException e) {
    isOk=false;
  }
  return null;
}
