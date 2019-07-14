@NotNull private String markup(String path){
  String source;
  try {
    source=_.readFile(path);
  }
 catch (  Exception e) {
    _.die("Failed to read file: " + path);
    return "";
  }
  List<Style> styles=new ArrayList<>();
  styles.addAll(linker.getStyles(path));
  String styledSource=new StyleApplier(path,source,styles).apply();
  String outline=new HtmlOutline(analyzer).generate(path);
  StringBuilder sb=new StringBuilder();
  sb.append("<html><head title=\"").append(path).append("\">").append("<style type='text/css'>\n").append(CSS).append("</style>\n").append("<script language=\"JavaScript\" type=\"text/javascript\">\n").append(Analyzer.self.hasOption("debug") ? JS_DEBUG : JS).append("</script>\n").append("</head>\n<body>\n").append("<table width=100% border='1px solid gray'><tr><td valign='top'>").append(outline).append("</td><td>").append("<pre>").append(addLineNumbers(styledSource)).append("</pre>").append("</td></tr></table></body></html>");
  return sb.toString();
}
