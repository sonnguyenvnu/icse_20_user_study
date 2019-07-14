private String markup(String path) throws Exception {
  String source=Util.readFile(path);
  List<StyleRun> styles=new Styler(indexer,linker).addStyles(path,source);
  styles.addAll(linker.getStyles(path));
  String styledSource=new StyleApplier(path,source,styles).apply();
  String outline=new HtmlOutline(indexer).generate(path);
  StringBuilder sb=new StringBuilder();
  sb.append("<html><head title=\"").append(path).append("\">").append("<style type='text/css'>\n").append(CSS).append("</style>\n").append(JS).append("</head>\n<body>\n").append("<table width=100% border='1px solid gray'><tr><td valign='top'>").append(outline).append("</td><td>").append("<pre>").append(addLineNumbers(styledSource)).append("</pre>").append("</td></tr></table></body></html>");
  return sb.toString();
}
