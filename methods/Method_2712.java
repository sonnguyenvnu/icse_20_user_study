public static EasyDictionary create(String path){
  EasyDictionary dictionary=new EasyDictionary();
  if (dictionary.load(path)) {
    return dictionary;
  }
 else {
    logger.warning("?" + path + "????");
  }
  return null;
}
