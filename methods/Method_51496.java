@Override public void end() throws IOException {
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder(500);
  for (  Report.ProcessingError error : errors) {
    buf.setLength(0);
    buf.append(error.getFile());
    buf.append("\t-\t").append(error.getMsg()).append(PMD.EOL);
    writer.write(buf.toString());
  }
  for (  Report.SuppressedViolation excluded : suppressed) {
    buf.setLength(0);
    buf.append(excluded.getRuleViolation().getRule().getName());
    buf.append(" rule violation suppressed by ");
    buf.append(excluded.suppressedByNOPMD() ? "//NOPMD" : "Annotation");
    buf.append(" in ").append(excluded.getRuleViolation().getFilename()).append(PMD.EOL);
    writer.write(buf.toString());
  }
  for (  Report.ConfigurationError error : configErrors) {
    buf.setLength(0);
    buf.append(error.rule().getName());
    buf.append("\t-\t").append(error.issue()).append(PMD.EOL);
    writer.write(buf.toString());
  }
}
