/** 
 * @nowebref
 */
public XML loadXML(String filename,String options){
  try {
    BufferedReader reader=createReader(filename);
    if (reader != null) {
      return new XML(reader,options);
    }
    return null;
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
catch (  ParserConfigurationException e) {
    throw new RuntimeException(e);
  }
catch (  SAXException e) {
    throw new RuntimeException(e);
  }
}
