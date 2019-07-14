/** 
 * Called when target not found. By default sends 404 to the response.
 */
protected void targetNotFound(final ActionRequest actionRequest,final String actionAndResultPath) throws IOException {
  final HttpServletResponse response=actionRequest.getHttpServletResponse();
  if (!response.isCommitted()) {
    response.sendError(SC_NOT_FOUND,"Result not found: " + actionAndResultPath);
  }
}
