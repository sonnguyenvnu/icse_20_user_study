/** 
 * ????
 * @param mainPath ???????
 * @param path ?????
 * @param isCache ??????
 */
public static boolean loadMainDictionary(String mainPath,String path[],DoubleArrayTrie<CoreDictionary.Attribute> dat,boolean isCache){
  logger.info("?????????:" + mainPath);
  if (loadDat(mainPath,dat))   return true;
  TreeMap<String,CoreDictionary.Attribute> map=new TreeMap<String,CoreDictionary.Attribute>();
  LinkedHashSet<Nature> customNatureCollector=new LinkedHashSet<Nature>();
  try {
    for (    String p : path) {
      Nature defaultNature=Nature.n;
      File file=new File(p);
      String fileName=file.getName();
      int cut=fileName.lastIndexOf(' ');
      if (cut > 0) {
        String nature=fileName.substring(cut + 1);
        p=file.getParent() + File.separator + fileName.substring(0,cut);
        try {
          defaultNature=LexiconUtility.convertStringToNature(nature,customNatureCollector);
        }
 catch (        Exception e) {
          logger.severe("?????" + p + "?????" + e);
          continue;
        }
      }
      logger.info("?????[" + defaultNature + "]???????" + p + "?……");
      boolean success=load(p,defaultNature,map,customNatureCollector);
      if (!success)       logger.warning("???" + p);
    }
    if (map.size() == 0) {
      logger.warning("?????????");
      map.put(Predefine.TAG_OTHER,null);
    }
    logger.info("????DoubleArrayTrie……");
    dat.build(map);
    if (isCache) {
      logger.info("???????dat??……");
      List<CoreDictionary.Attribute> attributeList=new LinkedList<CoreDictionary.Attribute>();
      for (      Map.Entry<String,CoreDictionary.Attribute> entry : map.entrySet()) {
        attributeList.add(entry.getValue());
      }
      DataOutputStream out=new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(mainPath + Predefine.BIN_EXT)));
      if (customNatureCollector.isEmpty()) {
        for (int i=Nature.begin.ordinal() + 1; i < Nature.values().length; ++i) {
          customNatureCollector.add(Nature.values()[i]);
        }
      }
      IOUtil.writeCustomNature(out,customNatureCollector);
      out.writeInt(attributeList.size());
      for (      CoreDictionary.Attribute attribute : attributeList) {
        attribute.save(out);
      }
      dat.save(out);
      out.close();
    }
  }
 catch (  FileNotFoundException e) {
    logger.severe("?????" + mainPath + "????" + e);
    return false;
  }
catch (  IOException e) {
    logger.severe("?????" + mainPath + "?????" + e);
    return false;
  }
catch (  Exception e) {
    logger.warning("?????" + mainPath + "?????\n" + TextUtility.exceptionToString(e));
  }
  return true;
}
