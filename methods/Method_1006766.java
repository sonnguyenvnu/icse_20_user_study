/** 
 * ?????????excel??
 * @throws Exception
 * @throws JDOMException
 */
@SuppressWarnings("unchecked") public void init() throws Exception {
  File configFile=new File("/");
  if (configFile.lastModified() <= configLastModifiedTime)   return;
  configLastModifiedTime=configFile.lastModified();
  InputStream in=new FileInputStream(configFile);
  SAXBuilder builder=new SAXBuilder(false);
  Document doc=builder.build(in);
  Element configRoot=doc.getRootElement();
  List<?> configList=configRoot.getChildren("book");
  fileNames.clear();
  for (  Object configObject : configList) {
    Element configItem=(Element)configObject;
    String fileName=configItem.getAttributeValue("name");
    fileNames.add(fileName);
    Map<String,Class<? extends ITemplateable>> sheetClassMap=new HashMap<String,Class<? extends ITemplateable>>();
    List<String> sheetNameList=new ArrayList<String>();
    fileSheetClassMap.put(fileName,sheetClassMap);
    fileSheetNameClassMap.put(fileName,sheetNameList);
    List<Element> sheetElementList=configItem.getChildren("sheet");
    for (    Element sheetE : sheetElementList) {
      String sheetName=sheetE.getAttributeValue("name");
      try {
        Class<ITemplateable> clz=(Class<ITemplateable>)Class.forName(sheetE.getAttributeValue("type"));
        sheetClassMap.put(sheetName,clz);
        sheetNameList.add(sheetName);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}
