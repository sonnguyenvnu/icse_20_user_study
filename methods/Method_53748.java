private static void flush(StringBuilder out,List<Match> matches,boolean altColor){
  Match ref=matches.get(0);
  if (matches.size() == 1 && ref.rowClass.equals(altColor ? "altColor" : "rowColor")) {
    out.append(ref.original);
    return;
  }
  out.append("<tr class=\"").append(altColor ? "altColor" : "rowColor").append("\">\n<td class=\"colFirst\"><code>").append(ref.type).append("</code></td>\n<th class=\"colSecond\"><code><span class=\"memberNameLink\">");
  for (  Match contiguous : matches) {
    if (contiguous != ref) {
      out.append("\n<br>");
    }
    out.append("<a href=\"").append(contiguous.link).append("\">").append(contiguous.name).append("</a>");
  }
  out.append("</span></code></th>\n<td class=\"colLast\">");
  if (ref.comment.isEmpty()) {
    out.append("&nbsp;");
  }
 else {
    out.append("\n<div class=\"block\">").append(ref.comment).append("</div>\n");
  }
  out.append("</td>\n</tr>").append(ref.nl);
}
