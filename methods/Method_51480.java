@Override public void start() throws IOException {
  Writer writer=getWriter();
  writer.write("<html><head><title>PMD</title></head><body>" + PMD.EOL);
  writer.write("<center><h3>PMD report</h3></center>");
  writer.write("<center><h3>Problems found</h3></center>");
  writer.write("<table align=\"center\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + PMD.EOL + "<th>#</th><th>File</th><th>Line</th><th>Problem</th></tr>" + PMD.EOL);
}
