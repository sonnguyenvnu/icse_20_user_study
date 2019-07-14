public static String detectCharset(String contentType,byte[] contentBytes) throws IOException {
  String charset;
  charset=UrlUtils.getCharset(contentType);
  if (StringUtils.isNotBlank(contentType) && StringUtils.isNotBlank(charset)) {
    logger.debug("Auto get charset: {}",charset);
    return charset;
  }
  Charset defaultCharset=Charset.defaultCharset();
  String content=new String(contentBytes,defaultCharset);
  if (StringUtils.isNotEmpty(content)) {
    Document document=Jsoup.parse(content);
    Elements links=document.select("meta");
    for (    Element link : links) {
      String metaContent=link.attr("content");
      String metaCharset=link.attr("charset");
      if (metaContent.indexOf("charset") != -1) {
        metaContent=metaContent.substring(metaContent.indexOf("charset"),metaContent.length());
        charset=metaContent.split("=")[1];
        break;
      }
 else       if (StringUtils.isNotEmpty(metaCharset)) {
        charset=metaCharset;
        break;
      }
    }
  }
  logger.debug("Auto get charset: {}",charset);
  return charset;
}
