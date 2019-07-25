public static StringBuilder dirhtml(final String base,final String servermessage,final String greeting,final String system,final List<String> list,final boolean metaRobotNoindex){
  final StringBuilder page=new StringBuilder(1024);
  final String title="Index of " + base;
  page.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">\n");
  page.append("<html><head>\n");
  page.append("  <title>").append(title).append("</title>\n");
  page.append("  <meta name=\"generator\" content=\"YaCy directory listing\">\n");
  if (metaRobotNoindex) {
    page.append("  <meta name=\"robots\" content=\"noindex\">\n");
  }
  page.append("  <base href=\"").append(base).append("\">\n");
  page.append("</head><body>\n");
  page.append("  <h1>").append(title).append("</h1>\n");
  if (servermessage != null && greeting != null) {
    page.append("  <p><pre>Server \"").append(servermessage).append("\" responded:\n");
    page.append("  \n");
    page.append(greeting);
    page.append("\n");
    page.append("  </pre></p>\n");
  }
  page.append("  <hr>\n");
  page.append("  <pre>\n");
  int nameStart, nameEnd;
  entryInfo info;
  for (  final String line : list) {
    info=parseListData(line);
    if (info != null) {
      nameStart=line.indexOf(info.name);
      page.append(line.substring(0,nameStart));
      page.append("<a href=\"").append(base).append(info.name).append((info.type == filetype.directory) ? "/" : "").append("\">").append(info.name).append("</a>");
      nameEnd=nameStart + info.name.length();
      if (line.length() > nameEnd) {
        page.append(line.substring(nameEnd));
      }
    }
 else     if (line.startsWith("http://") || line.startsWith("ftp://") || line.startsWith("smb://") || line.startsWith("file://")) {
      page.append("<a href=\"").append(line).append("\">").append(line).append("</a>");
    }
 else {
      page.append(line);
    }
    page.append('\n');
  }
  page.append("  </pre>\n");
  page.append("  <hr>\n");
  if (system != null)   page.append("  <pre>System info: \"").append(system).append("\"</pre>\n");
  page.append("</body></html>\n");
  return page;
}
