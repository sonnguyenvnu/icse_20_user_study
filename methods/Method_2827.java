/** 
 * ???BufferedReader?????????
 * @param br ?
 * @param storage ????
 * @throws IOException ????????
 */
public static void loadDictionary(BufferedReader br,TreeMap<String,CoreDictionary.Attribute> storage,boolean isCSV,Nature defaultNature) throws IOException {
  String splitter="\\s";
  if (isCSV) {
    splitter=",";
  }
  String line;
  boolean firstLine=true;
  while ((line=br.readLine()) != null) {
    if (firstLine) {
      line=IOUtil.removeUTF8BOM(line);
      firstLine=false;
    }
    String param[]=line.split(splitter);
    int natureCount=(param.length - 1) / 2;
    CoreDictionary.Attribute attribute;
    if (natureCount == 0) {
      attribute=new CoreDictionary.Attribute(defaultNature);
    }
 else {
      attribute=new CoreDictionary.Attribute(natureCount);
      for (int i=0; i < natureCount; ++i) {
        attribute.nature[i]=LexiconUtility.convertStringToNature(param[1 + 2 * i]);
        attribute.frequency[i]=Integer.parseInt(param[2 + 2 * i]);
        attribute.totalFrequency+=attribute.frequency[i];
      }
    }
    storage.put(param[0],attribute);
  }
  br.close();
}
