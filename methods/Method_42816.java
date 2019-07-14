/** 
 * @param request ??
 * @return ????????
 * @throws IOException
 */
public String parseRequestString(HttpServletRequest request) throws IOException {
  String inputLine;
  String notityXml="";
  while ((inputLine=request.getReader().readLine()) != null) {
    notityXml+=inputLine;
  }
  request.getReader().close();
  return notityXml;
}
