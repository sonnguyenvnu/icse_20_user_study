private void glomProcessingErrors(Writer writer,List<Report.ProcessingError> errors) throws IOException {
  if (errors.isEmpty()) {
    return;
  }
  writer.write("<hr/>");
  writer.write("<center><h3>Processing errors</h3></center>");
  writer.write("<table align=\"center\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + PMD.EOL + "<th>File</th><th>Problem</th></tr>" + PMD.EOL);
  StringBuffer buf=new StringBuffer(500);
  boolean colorize=true;
  for (  Report.ProcessingError pe : errors) {
    buf.setLength(0);
    buf.append("<tr");
    if (colorize) {
      buf.append(" bgcolor=\"lightgrey\"");
    }
    colorize=!colorize;
    buf.append("> " + PMD.EOL);
    buf.append("<td>" + pe.getFile() + "</td>" + PMD.EOL);
    buf.append("<td><pre>" + pe.getDetail() + "</pre></td>" + PMD.EOL);
    buf.append("</tr>" + PMD.EOL);
    writer.write(buf.toString());
  }
  writer.write("</table>");
}
