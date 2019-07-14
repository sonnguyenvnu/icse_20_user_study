@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  Writer writer=getWriter();
  StringBuffer buf=new StringBuffer();
  while (violations.hasNext()) {
    RuleViolation rv=violations.next();
    buf.setLength(0);
    buf.append(rv.getFilename() + "(");
    buf.append(Integer.toString(rv.getBeginLine())).append(",  ");
    buf.append(rv.getRule().getName()).append("):  ");
    buf.append(rv.getDescription()).append(PMD.EOL);
    writer.write(buf.toString());
  }
}
