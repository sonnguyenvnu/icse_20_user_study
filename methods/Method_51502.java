@Override public void end() throws IOException {
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder(500);
  for (  Report.ProcessingError pe : errors) {
    buf.setLength(0);
    buf.append("<error ").append("filename=\"");
    StringUtil.appendXmlEscaped(buf,pe.getFile(),useUTF8);
    buf.append("\" msg=\"");
    StringUtil.appendXmlEscaped(buf,pe.getMsg(),useUTF8);
    buf.append("\">").append(PMD.EOL);
    buf.append("<![CDATA[").append(pe.getDetail()).append("]]>").append(PMD.EOL);
    buf.append("</error>").append(PMD.EOL);
    writer.write(buf.toString());
  }
  if (showSuppressedViolations) {
    for (    Report.SuppressedViolation s : suppressed) {
      buf.setLength(0);
      buf.append("<suppressedviolation ").append("filename=\"");
      StringUtil.appendXmlEscaped(buf,s.getRuleViolation().getFilename(),useUTF8);
      buf.append("\" suppressiontype=\"");
      StringUtil.appendXmlEscaped(buf,s.suppressedByNOPMD() ? "nopmd" : "annotation",useUTF8);
      buf.append("\" msg=\"");
      StringUtil.appendXmlEscaped(buf,s.getRuleViolation().getDescription(),useUTF8);
      buf.append("\" usermsg=\"");
      StringUtil.appendXmlEscaped(buf,s.getUserMessage() == null ? "" : s.getUserMessage(),useUTF8);
      buf.append("\"/>").append(PMD.EOL);
      writer.write(buf.toString());
    }
  }
  for (  final Report.ConfigurationError ce : configErrors) {
    buf.setLength(0);
    buf.append("<configerror ").append("rule=\"");
    StringUtil.appendXmlEscaped(buf,ce.rule().getName(),useUTF8);
    buf.append("\" msg=\"");
    StringUtil.appendXmlEscaped(buf,ce.issue(),useUTF8);
    buf.append("\"/>").append(PMD.EOL);
    writer.write(buf.toString());
  }
  writer.write("</pmd>" + PMD.EOL);
}
