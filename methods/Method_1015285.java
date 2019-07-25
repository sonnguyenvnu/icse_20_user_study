/** 
 * ????
 * @throws Exception
 */
public static void save(Document doc,String xmlPath,String encoding) throws Exception {
  OutputFormat format=OutputFormat.createPrettyPrint();
  format.setEncoding(encoding);
  XMLWriter writer=new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath),encoding),format);
  writer.write(doc);
  writer.flush();
  writer.close();
}
