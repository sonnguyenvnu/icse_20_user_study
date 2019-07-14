@Override public void endFile(){
  if (batchRequest != null) {
    sendBatch(rows);
  }
  BatchUpdateSpreadsheetRequest requestBody=new BatchUpdateSpreadsheetRequest();
  requestBody.setIncludeSpreadsheetInResponse(false);
  requestBody.setRequests(requests);
  Sheets.Spreadsheets.BatchUpdate request;
  try {
    logger.debug("spreadsheetId: " + spreadsheetId);
    logger.debug("requestBody:" + requestBody.toString());
    request=service.spreadsheets().batchUpdate(spreadsheetId,requestBody);
    BatchUpdateSpreadsheetResponse response=request.execute();
    logger.debug("response:" + response.toPrettyString());
  }
 catch (  IOException e) {
    exceptions.add(e);
  }
}
