protected void odsParse(InputStream input,String worksheet,boolean header){
  try {
    InputStream contentStream=odsFindContentXML(input);
    XML xml=new XML(contentStream);
    XML[] sheets=xml.getChildren("office:body/office:spreadsheet/table:table");
    boolean found=false;
    for (    XML sheet : sheets) {
      if (worksheet == null || worksheet.equals(sheet.getString("table:name"))) {
        odsParseSheet(sheet,header);
        found=true;
        if (worksheet == null) {
          break;
        }
      }
    }
    if (!found) {
      if (worksheet == null) {
        throw new RuntimeException("No worksheets found in the ODS file.");
      }
 else {
        throw new RuntimeException("No worksheet named " + worksheet + " found in the ODS file.");
      }
    }
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
catch (  IOException e) {
    e.printStackTrace();
  }
catch (  ParserConfigurationException e) {
    e.printStackTrace();
  }
catch (  SAXException e) {
    e.printStackTrace();
  }
}
