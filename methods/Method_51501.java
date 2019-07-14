@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder(500);
  String filename=null;
  while (violations.hasNext()) {
    buf.setLength(0);
    RuleViolation rv=violations.next();
    if (!rv.getFilename().equals(filename)) {
      if (filename != null) {
        buf.append("</file>").append(PMD.EOL);
      }
      filename=rv.getFilename();
      buf.append("<file name=\"");
      StringUtil.appendXmlEscaped(buf,filename,useUTF8);
      buf.append("\">").append(PMD.EOL);
    }
    buf.append("<violation beginline=\"").append(rv.getBeginLine());
    buf.append("\" endline=\"").append(rv.getEndLine());
    buf.append("\" begincolumn=\"").append(rv.getBeginColumn());
    buf.append("\" endcolumn=\"").append(rv.getEndColumn());
    buf.append("\" rule=\"");
    StringUtil.appendXmlEscaped(buf,rv.getRule().getName(),useUTF8);
    buf.append("\" ruleset=\"");
    StringUtil.appendXmlEscaped(buf,rv.getRule().getRuleSetName(),useUTF8);
    buf.append('"');
    maybeAdd("package",rv.getPackageName(),buf);
    maybeAdd("class",rv.getClassName(),buf);
    maybeAdd("method",rv.getMethodName(),buf);
    maybeAdd("variable",rv.getVariableName(),buf);
    maybeAdd("externalInfoUrl",rv.getRule().getExternalInfoUrl(),buf);
    buf.append(" priority=\"");
    buf.append(rv.getRule().getPriority().getPriority());
    buf.append("\">").append(PMD.EOL);
    StringUtil.appendXmlEscaped(buf,rv.getDescription(),useUTF8);
    buf.append(PMD.EOL);
    buf.append("</violation>");
    buf.append(PMD.EOL);
    writer.write(buf.toString());
  }
  if (filename != null) {
    writer.write("</file>");
    writer.write(PMD.EOL);
  }
}
