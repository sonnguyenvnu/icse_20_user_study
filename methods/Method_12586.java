public static IkTokenizerFactory getIkTokenizerFactory(IndexSettings indexSettings,Environment env,String name,Settings settings){
  return new IkTokenizerFactory(indexSettings,env,name,settings).setSmart(false);
}
