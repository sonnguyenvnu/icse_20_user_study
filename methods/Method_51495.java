@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder();
  while (violations.hasNext()) {
    buf.setLength(0);
    RuleViolation rv=violations.next();
    buf.append(rv.getFilename());
    buf.append(':').append(Integer.toString(rv.getBeginLine()));
    buf.append(":\t").append(rv.getDescription()).append(PMD.EOL);
    writer.write(buf.toString());
  }
}
