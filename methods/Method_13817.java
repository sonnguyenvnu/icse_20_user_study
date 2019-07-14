/** 
 * @param request
 * @param response
 * @param parameters
 * @throws ServletException
 * @throws IOException
 */
private void doInitializeParserUI(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  if (logger.isDebugEnabled()) {
    logger.debug("::doInitializeParserUI::");
  }
  ObjectNode result=ParsingUtilities.mapper.createObjectNode();
  ObjectNode options=ParsingUtilities.mapper.createObjectNode();
  JSONUtilities.safePut(result,"status","ok");
  JSONUtilities.safePut(result,OPTIONS_KEY,options);
  JSONUtilities.safePut(options,"skipDataLines",0);
  JSONUtilities.safePut(options,"storeBlankRows",true);
  JSONUtilities.safePut(options,"storeBlankCellsAsNulls",true);
  if (logger.isDebugEnabled()) {
    logger.debug("doInitializeParserUI:::{}",result.toString());
  }
  HttpUtilities.respond(response,result.toString());
}
