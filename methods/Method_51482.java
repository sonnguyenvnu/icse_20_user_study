@Override public void end() throws IOException {
  Writer writer=getWriter();
  writer.write("</table>");
  glomProcessingErrors(writer,errors);
  if (showSuppressedViolations) {
    glomSuppressions(writer,suppressed);
  }
  glomConfigurationErrors(writer,configErrors);
  writer.write("</body></html>" + PMD.EOL);
}
