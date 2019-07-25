/** 
 * ????? key
 * @param fileName
 * @return
 * @throws IOException
 */
public static List<String> getkeys(String fileName) throws IOException {
  Properties prop=new Properties();
  InputStream in=PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
  prop.load(in);
  Set keyValue=prop.keySet();
  List<String> list=new ArrayList<>();
  for (Iterator it=keyValue.iterator(); it.hasNext(); ) {
    String key=(String)it.next();
    list.add(key);
  }
  return list;
}
