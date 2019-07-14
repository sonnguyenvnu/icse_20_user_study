private void renderClass(ClassNode cnode){
  String str=cnode.getClassName();
  classBuf.insert(0,"<!DOCTYPE html>" + PMD.EOL + "<html><head><meta charset=\"UTF-8\"><title>PMD - " + str + "</title></head><body>" + PMD.EOL + "<h2>Class View</h2>" + "<h3 align=\"center\">Class: " + str + "</h3>" + "<table border=\"\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">" + " <tr>" + PMD.EOL + "<th>Method</th>" + "<th>Violation</th>" + " </tr>" + PMD.EOL);
  classBuf.append("</table>" + " </body>" + "</html>");
  try {
    write(str + ".html",classBuf);
  }
 catch (  Exception e) {
    throw new RuntimeException("Error while writing HTML report: " + e.getMessage());
  }
  classBuf=new StringBuilder();
  packageBuf.insert(this.length,"<tr>" + " <td>-</td>" + " <td><a href=\"" + str + ".html\">" + str + "</a></td>" + " <td>" + cnode.getNumberOfViolations() + "</td>" + "</tr>" + PMD.EOL);
  cnode.getParent().addNumberOfViolation(cnode.getNumberOfViolations());
}
