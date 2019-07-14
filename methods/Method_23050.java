void send404(PrintStream ps) throws IOException {
  ps.write(EOL);
  ps.write(EOL);
  ps.print("<html><body><h1>404 Not Found</h1>" + "The requested resource was not found.</body></html>");
  ps.write(EOL);
  ps.write(EOL);
}
