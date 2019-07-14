protected String listDirectory(String uri,File f){
  String heading="Directory " + uri;
  StringBuilder msg=new StringBuilder("<html><head><title>" + heading + "</title><style><!--\n" + "span.dirname { font-weight: bold; }\n" + "span.filesize { font-size: 75%; }\n" + "// -->\n" + "</style>" + "</head><body><h1>" + heading + "</h1>");
  String up=null;
  if (uri.length() > 1) {
    String u=uri.substring(0,uri.length() - 1);
    int slash=u.lastIndexOf('/');
    if (slash >= 0 && slash < u.length()) {
      up=uri.substring(0,slash + 1);
    }
  }
  List<String> files=Arrays.asList(f.list(new FilenameFilter(){
    @Override public boolean accept(    File dir,    String name){
      return new File(dir,name).isFile();
    }
  }
));
  Collections.sort(files);
  List<String> directories=Arrays.asList(f.list(new FilenameFilter(){
    @Override public boolean accept(    File dir,    String name){
      return new File(dir,name).isDirectory();
    }
  }
));
  Collections.sort(directories);
  if (up != null || directories.size() + files.size() > 0) {
    msg.append("<ul>");
    if (up != null || directories.size() > 0) {
      msg.append("<section class=\"directories\">");
      if (up != null) {
        msg.append("<li><a rel=\"directory\" href=\"").append(up).append("\"><span class=\"dirname\">..</span></a></li>");
      }
      for (      String directory : directories) {
        String dir=directory + "/";
        msg.append("<li><a rel=\"directory\" href=\"").append(encodeUri(uri + dir)).append("\"><span class=\"dirname\">").append(dir).append("</span></a></li>");
      }
      msg.append("</section>");
    }
    if (files.size() > 0) {
      msg.append("<section class=\"files\">");
      for (      String file : files) {
        msg.append("<li><a href=\"").append(encodeUri(uri + file)).append("\"><span class=\"filename\">").append(file).append("</span></a>");
        File curFile=new File(f,file);
        long len=curFile.length();
        msg.append("&nbsp;<span class=\"filesize\">(");
        if (len < 1024) {
          msg.append(len).append(" bytes");
        }
 else         if (len < 1024 * 1024) {
          msg.append(len / 1024).append(".").append(len % 1024 / 10 % 100).append(" KB");
        }
 else {
          msg.append(len / (1024 * 1024)).append(".").append(len % (1024 * 1024) / 10000 % 100).append(" MB");
        }
        msg.append(")</span></li>");
      }
      msg.append("</section>");
    }
    msg.append("</ul>");
  }
  msg.append("</body></html>");
  return msg.toString();
}
