/** 
 * Creates a 'sessioninfo' element.
 * @param info info object to write out
 * @throws IOException in case of problems with the underlying output
 */
public void sessioninfo(final SessionInfo info) throws IOException {
  final ReportElement sessioninfo=element("sessioninfo");
  sessioninfo.attr("id",info.getId());
  sessioninfo.attr("start",info.getStartTimeStamp());
  sessioninfo.attr("dump",info.getDumpTimeStamp());
}
