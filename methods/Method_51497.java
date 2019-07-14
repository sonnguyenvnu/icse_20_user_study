@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  if (!violations.hasNext()) {
    return;
  }
  Writer writer=getWriter();
  StringBuilder sb=new StringBuilder(500);
  String filename=null;
  String lineSep=PMD.EOL;
  boolean colorize=false;
  while (violations.hasNext()) {
    sb.setLength(0);
    RuleViolation rv=violations.next();
    if (!rv.getFilename().equals(filename)) {
      if (filename != null) {
        sb.append("</table></br>");
        colorize=false;
      }
      filename=rv.getFilename();
      sb.append("<table border=\"0\" width=\"80%\">");
      sb.append("<tr id=TableHeader><td colspan=\"2\"><font class=title>&nbsp;").append(filename).append("</font></tr>");
      sb.append(lineSep);
    }
    if (colorize) {
      sb.append("<tr id=RowColor1>");
    }
 else {
      sb.append("<tr id=RowColor2>");
    }
    colorize=!colorize;
    sb.append("<td width=\"50\" align=\"right\"><font class=body>" + rv.getBeginLine() + "&nbsp;&nbsp;&nbsp;</font></td>");
    sb.append("<td><font class=body>" + rv.getDescription() + "</font></td>");
    sb.append("</tr>");
    sb.append(lineSep);
    writer.write(sb.toString());
  }
  if (filename != null) {
    writer.write("</table>");
  }
}
