private void renderIndex(String outputDir) throws IOException {
  try (PrintWriter out=new PrintWriter(Files.newBufferedWriter(new File(outputDir,"index.html").toPath(),StandardCharsets.UTF_8))){
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("    <head>");
    out.println("        <meta charset=\"UTF-8\">");
    out.println("        <title>PMD</title>");
    out.println("    </head>");
    out.println("    <body>");
    out.println("    <h2>Package View</h2>");
    out.println("    <table border=\"1\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
    out.println("        <tr><th>Package</th><th>Class</th><th>#</th></tr>");
    for (    ReportNode node : reportNodesByPackage.values()) {
      out.print("        <tr><td><b>");
      out.print(node.getPackageName());
      out.print("</b></td> <td>");
      if (node.hasViolations()) {
        out.print("<a href=\"");
        out.print(node.getClassName());
        out.print(".html");
        out.print("\">");
        out.print(node.getClassName());
        out.print("</a>");
      }
 else {
        out.print(node.getClassName());
      }
      out.print("</td> <td>");
      out.print(node.getViolationCount());
      out.print("</td></tr>");
      out.println();
    }
    out.println("    </table>");
    out.println("    </body>");
    out.println("</html>");
  }
 }
