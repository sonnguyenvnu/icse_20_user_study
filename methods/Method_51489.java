private void renderFile(Writer writer,Iterator<RuleViolation> violations) throws IOException {
  StringBuilder buf=new StringBuilder();
  while (violations.hasNext()) {
    buf.setLength(0);
    RuleViolation rv=violations.next();
    buf.append(rv.getDescription()).append(PMD.EOL);
    buf.append(" at ").append(classAndMethodName).append('(').append(fileName).append(':').append(rv.getBeginLine()).append(')').append(PMD.EOL);
    writer.write(buf.toString());
  }
}
