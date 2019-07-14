private void glomSuppressions(Writer writer,List<Report.SuppressedViolation> suppressed) throws IOException {
  if (suppressed.isEmpty()) {
    return;
  }
  writer.write("<hr/>");
  writer.write("<center><h3>Suppressed warnings</h3></center>");
  writer.write("<table align=\"center\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + PMD.EOL + "<th>File</th><th>Line</th><th>Rule</th><th>NOPMD or Annotation</th><th>Reason</th></tr>" + PMD.EOL);
  StringBuilder buf=new StringBuilder(500);
  boolean colorize=true;
  for (  Report.SuppressedViolation sv : suppressed) {
    buf.setLength(0);
    buf.append("<tr");
    if (colorize) {
      buf.append(" bgcolor=\"lightgrey\"");
    }
    colorize=!colorize;
    buf.append("> " + PMD.EOL);
    buf.append("<td align=\"left\">" + sv.getRuleViolation().getFilename() + "</td>" + PMD.EOL);
    buf.append("<td align=\"center\">" + sv.getRuleViolation().getBeginLine() + "</td>" + PMD.EOL);
    buf.append("<td align=\"center\">" + sv.getRuleViolation().getRule().getName() + "</td>" + PMD.EOL);
    buf.append("<td align=\"center\">" + (sv.suppressedByNOPMD() ? "NOPMD" : "Annotation") + "</td>" + PMD.EOL);
    buf.append("<td align=\"center\">" + (sv.getUserMessage() == null ? "" : sv.getUserMessage()) + "</td>" + PMD.EOL);
    buf.append("</tr>" + PMD.EOL);
    writer.write(buf.toString());
  }
  writer.write("</table>");
}
