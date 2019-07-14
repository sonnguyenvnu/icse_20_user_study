private void doInitializeParserUI(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  String token=TokenCookie.getToken(request);
  String type=parameters.getProperty("docType");
  String urlString=parameters.getProperty("docUrl");
  ObjectNode result=ParsingUtilities.mapper.createObjectNode();
  ObjectNode options=ParsingUtilities.mapper.createObjectNode();
  JSONUtilities.safePut(result,"status","ok");
  JSONUtilities.safePut(result,"options",options);
  JSONUtilities.safePut(options,"skipDataLines",0);
  JSONUtilities.safePut(options,"storeBlankRows",true);
  JSONUtilities.safePut(options,"storeBlankCellsAsNulls",true);
  if ("spreadsheet".equals(type)) {
    ArrayNode worksheets=ParsingUtilities.mapper.createArrayNode();
    String spreadSheetId=GoogleAPIExtension.extractSpreadSheetId(urlString);
    JSONUtilities.safePut(options,"ignoreLines",-1);
    JSONUtilities.safePut(options,"headerLines",1);
    JSONUtilities.safePut(options,"worksheets",worksheets);
    List<Sheet> worksheetEntries=getWorksheetEntriesForDoc(token,spreadSheetId);
    for (    Sheet sheet : worksheetEntries) {
      ObjectNode worksheetO=ParsingUtilities.mapper.createObjectNode();
      JSONUtilities.safePut(worksheetO,"name",sheet.getProperties().getTitle());
      JSONUtilities.safePut(worksheetO,"rows",sheet.getProperties().getGridProperties().getRowCount());
      JSONUtilities.safePut(worksheetO,"link","https://sheets.googleapis.com/v4/spreadsheets/" + spreadSheetId + "/values/" + sheet.getProperties().getTitle());
      JSONUtilities.append(worksheets,worksheetO);
    }
  }
 else   if ("table".equals(type)) {
  }
  HttpUtilities.respond(response,result.toString());
}
