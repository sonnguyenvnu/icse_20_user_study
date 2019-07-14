private void glomConfigurationErrors(final Writer writer,final List<ConfigurationError> configErrors) throws IOException {
  if (configErrors.isEmpty()) {
    return;
  }
  writer.write("<hr/>");
  writer.write("<center><h3>Configuration errors</h3></center>");
  writer.write("<table align=\"center\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + PMD.EOL + "<th>Rule</th><th>Problem</th></tr>" + PMD.EOL);
  StringBuilder buf=new StringBuilder(500);
  boolean colorize=true;
  for (  Report.ConfigurationError ce : configErrors) {
    buf.setLength(0);
    buf.append("<tr");
    if (colorize) {
      buf.append(" bgcolor=\"lightgrey\"");
    }
    colorize=!colorize;
    buf.append("> " + PMD.EOL);
    buf.append("<td>" + ce.rule().getName() + "</td>" + PMD.EOL);
    buf.append("<td>" + ce.issue() + "</td>" + PMD.EOL);
    buf.append("</tr>" + PMD.EOL);
    writer.write(buf.toString());
  }
  writer.write("</table>");
}
