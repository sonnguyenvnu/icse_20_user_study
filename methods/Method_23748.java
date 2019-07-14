protected void writeHTML(PrintWriter writer){
  writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
  writer.println("<html>");
  writer.println("<head>");
  writer.println("  <meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />");
  writer.println("</head>");
  writer.println("<body>");
  writer.println("  <table>");
  if (hasColumnTitles()) {
    writer.println("  <tr>");
    for (    String entry : getColumnTitles()) {
      writer.print("      <th>");
      if (entry != null) {
        writeEntryHTML(writer,entry);
      }
      writer.println("</th>");
    }
    writer.println("  </tr>");
  }
  for (int row=0; row < getRowCount(); row++) {
    writer.println("    <tr>");
    for (int col=0; col < getColumnCount(); col++) {
      String entry=getString(row,col);
      writer.print("      <td>");
      if (entry != null) {
        writeEntryHTML(writer,entry);
      }
      writer.println("</td>");
    }
    writer.println("    </tr>");
  }
  writer.println("  </table>");
  writer.println("</body>");
  writer.println("</html>");
  writer.flush();
}
