private static void convert(String source) throws Exception {
  for (  File f : new File(source).listFiles()) {
    if (!f.getName().endsWith(".properties")) {
      continue;
    }
    FileInputStream in=new FileInputStream(f);
    InputStreamReader r=new InputStreamReader(in,StandardCharsets.UTF_8);
    String s=IOUtils.readStringAndClose(r,-1);
    in.close();
    String name=f.getName();
    String utf8, html;
    if (name.startsWith("utf8")) {
      utf8=HtmlConverter.convertHtmlToString(s);
      html=HtmlConverter.convertStringToHtml(utf8);
      RandomAccessFile out=new RandomAccessFile("_" + name.substring(4),"rw");
      out.write(html.getBytes());
      out.setLength(out.getFilePointer());
      out.close();
    }
 else {
      new CheckTextFiles().checkOrFixFile(f,false,false);
      html=s;
      utf8=HtmlConverter.convertHtmlToString(html);
      utf8=StringUtils.javaDecode(utf8);
      FileOutputStream out=new FileOutputStream("_utf8" + f.getName());
      OutputStreamWriter w=new OutputStreamWriter(out,StandardCharsets.UTF_8);
      w.write(utf8);
      w.close();
      out.close();
    }
    String java=StringUtils.javaEncode(utf8);
    java=StringUtils.replaceAll(java,"\\r","\r");
    java=StringUtils.replaceAll(java,"\\n","\n");
    RandomAccessFile out=new RandomAccessFile("_java." + name,"rw");
    out.write(java.getBytes());
    out.setLength(out.getFilePointer());
    out.close();
  }
}
