private void glomRuleViolations(Writer writer,Iterator<RuleViolation> violations) throws IOException {
  StringBuilder buf=new StringBuilder(500);
  while (violations.hasNext()) {
    RuleViolation rv=violations.next();
    buf.setLength(0);
    buf.append("<tr");
    if (colorize) {
      buf.append(" bgcolor=\"lightgrey\"");
    }
    colorize=!colorize;
    buf.append("> " + PMD.EOL);
    buf.append("<td align=\"center\">" + violationCount + "</td>" + PMD.EOL);
    buf.append("<td width=\"*%\">" + maybeWrap(StringEscapeUtils.escapeHtml4(rv.getFilename()),linePrefix == null ? "" : linePrefix + Integer.toString(rv.getBeginLine())) + "</td>" + PMD.EOL);
    buf.append("<td align=\"center\" width=\"5%\">" + Integer.toString(rv.getBeginLine()) + "</td>" + PMD.EOL);
    String d=StringEscapeUtils.escapeHtml4(rv.getDescription());
    String infoUrl=rv.getRule().getExternalInfoUrl();
    if (StringUtils.isNotBlank(infoUrl)) {
      d="<a href=\"" + infoUrl + "\">" + d + "</a>";
    }
    buf.append("<td width=\"*\">" + d + "</td>" + PMD.EOL);
    buf.append("</tr>" + PMD.EOL);
    writer.write(buf.toString());
    violationCount++;
  }
}
