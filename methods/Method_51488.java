private void renderDirectoy(Writer writer,Iterator<RuleViolation> violations) throws IOException {
  SourcePath sourcePath=new SourcePath(getProperty(SOURCE_PATH));
  StringBuilder buf=new StringBuilder();
  while (violations.hasNext()) {
    buf.setLength(0);
    RuleViolation rv=violations.next();
    buf.append(rv.getDescription() + PMD.EOL);
    buf.append(" at ").append(getFullyQualifiedClassName(rv.getFilename(),sourcePath)).append(".method(");
    buf.append(getSimpleFileName(rv.getFilename())).append(':').append(rv.getBeginLine()).append(')').append(PMD.EOL);
    writer.write(buf.toString());
  }
}
