private static void flush(StringBuilder out,List<Match> matches){
  boolean methods=matches.stream().anyMatch(match -> match.code.indexOf('(') != -1);
  if (matches.size() == 1 && methods) {
    out.append(matches.get(0).original);
    return;
  }
  List<Match> distinct=matches.stream().distinct().collect(Collectors.toList());
  distinct.forEach(match -> out.append("<a id=\"").append(match.link).append("\">").append(match.linkContents).append("</a>\n"));
  out.append(distinct.get(distinct.size() - 1).block).append("<h4>").append(distinct.stream().map(match -> match.seeAlso == null ? match.name : "<a href=\"" + match.seeAlso + "\">" + match.name + "</a>").distinct().collect(Collectors.joining(", "))).append("</h4>\n");
  if (methods) {
    out.append("<pre>").append(distinct.stream().map(match -> match.code + "\n").collect(Collectors.joining("\n"))).append("</pre>\n");
  }
  out.append(distinct.get(0).documentation).append("</li></ul>\n");
}
