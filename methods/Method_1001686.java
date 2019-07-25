private void export(IEntity entity) throws IOException {
  final File f=new File(dir,LinkHtmlPrinter.urlOf(entity));
  final PrintWriter pw=new PrintWriter(f);
  pw.println("<html>");
  pw.println("<title>" + StringUtils.unicodeForHtml(entity.getCode().getFullName()) + "</title>");
  pw.println("<h2>" + entity.getLeafType().toHtml() + "</h2>");
  for (  CharSequence s : entity.getDisplay()) {
    pw.println(StringUtils.unicodeForHtml(s.toString()));
    pw.println("<br>");
  }
  final Stereotype stereotype=entity.getStereotype();
  if (stereotype != null) {
    pw.println("<hr>");
    pw.println("<h3>Stereotype</h3>");
    for (    String s : stereotype.getLabels(diagram.getSkinParam().guillemet())) {
      pw.println(s);
      pw.println("<br>");
    }
  }
  pw.println("<hr>");
  if (entity.getBodier().getFieldsToDisplay().size() == 0) {
    pw.println("<h2>No fields</h2>");
  }
 else {
    pw.println("<h2>Fields:</h2>");
    pw.println("<ul>");
    for (    Member m : entity.getBodier().getFieldsToDisplay()) {
      pw.println("<li>");
      pw.println(StringUtils.unicodeForHtml(m.getDisplay(true)));
      pw.println("</li>");
    }
    pw.println("</ul>");
  }
  pw.println("<hr>");
  if (entity.getBodier().getMethodsToDisplay().size() == 0) {
    pw.println("<h2>No methods</h2>");
  }
 else {
    pw.println("<h2>Methods:</h2>");
    pw.println("<ul>");
    for (    Member m : entity.getBodier().getMethodsToDisplay()) {
      pw.println("<li>");
      pw.println(StringUtils.unicodeForHtml(m.getDisplay(true)));
      pw.println("</li>");
    }
    pw.println("</ul>");
  }
  pw.println("<hr>");
  final Collection<Link> links=getLinksButNotes(entity);
  if (links.size() == 0) {
    pw.println("<h2>No links</h2>");
  }
 else {
    pw.println("<h2>Links:</h2>");
    pw.println("<ul>");
    for (    Link l : links) {
      pw.println("<li>");
      new LinkHtmlPrinter(l,entity).printLink(pw);
      pw.println("</li>");
    }
    pw.println("</ul>");
  }
  final Collection<IEntity> notes=getNotes(entity);
  if (notes.size() > 0) {
    pw.println("<hr>");
    pw.println("<h2>Notes:</h2>");
    pw.println("<ul>");
    for (    IEntity note : notes) {
      pw.println("<li>");
      for (      CharSequence s : note.getDisplay()) {
        pw.println(StringUtils.unicodeForHtml(s.toString()));
        pw.println("<br>");
      }
      pw.println("</li>");
    }
    pw.println("</ul>");
  }
  htmlClose(pw);
}
