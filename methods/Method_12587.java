public static IkTokenizerFactory getIkSmartTokenizerFactory(IndexSettings indexSettings,Environment env,String name,Settings settings){
  return new IkTokenizerFactory(indexSettings,env,name,settings).setSmart(true);
}
