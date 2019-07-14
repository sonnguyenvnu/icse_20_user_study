static ObjectNode loadLanguage(RefineServlet servlet,String modname,String lang) throws UnsupportedEncodingException {
  ButterflyModule module=servlet.getModule(modname);
  File langFile=new File(module.getPath(),"langs" + File.separator + "translation-" + lang + ".json");
  try {
    Reader reader=new BufferedReader(new InputStreamReader(new FileInputStream(langFile),"UTF-8"));
    return ParsingUtilities.mapper.readValue(reader,ObjectNode.class);
  }
 catch (  FileNotFoundException e1) {
  }
catch (  IOException e) {
    logger.error("JSON error reading/writing language file: " + langFile,e);
  }
  return null;
}
