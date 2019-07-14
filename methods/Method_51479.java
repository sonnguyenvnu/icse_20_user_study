@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder();
  while (violations.hasNext()) {
    RuleViolation rv=violations.next();
    buf.setLength(0);
    buf.append(rv.getFilename());
    buf.append(':').append(Integer.toString(rv.getBeginLine()));
    buf.append(": ").append(rv.getDescription()).append(EOL);
    writer.write(buf.toString());
  }
}
