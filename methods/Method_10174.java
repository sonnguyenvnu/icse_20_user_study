/** 
 * Gets the safe HTML content of the specified content.
 * @param content the specified content
 * @param baseURI the specified base URI, the relative path value of href will starts with this URL
 * @return safe HTML content
 */
public static String clean(final String content,final String baseURI){
  final Document.OutputSettings outputSettings=new Document.OutputSettings();
  outputSettings.prettyPrint(false);
  final Whitelist whitelist=Whitelist.relaxed().addAttributes(":all","id","target","class","data-src","aria-name","aria-label");
  inputWhitelist(whitelist);
  final String tmp=Jsoup.clean(content,baseURI,whitelist,outputSettings);
  final Document doc=Jsoup.parse(tmp,baseURI,Parser.htmlParser());
  final Elements ps=doc.getElementsByTag("p");
  for (  final Element p : ps) {
    p.removeAttr("style");
  }
  final Elements iframes=doc.getElementsByTag("iframe");
  for (  final Element iframe : iframes) {
    final String src=StringUtils.deleteWhitespace(iframe.attr("src"));
    if (StringUtils.startsWithIgnoreCase(src,"javascript") || StringUtils.startsWithIgnoreCase(src,"data:")) {
      iframe.remove();
    }
  }
  final Elements objs=doc.getElementsByTag("object");
  for (  final Element obj : objs) {
    final String data=StringUtils.deleteWhitespace(obj.attr("data"));
    if (StringUtils.startsWithIgnoreCase(data,"data:") || StringUtils.startsWithIgnoreCase(data,"javascript")) {
      obj.remove();
      continue;
    }
    final String type=StringUtils.deleteWhitespace(obj.attr("type"));
    if (StringUtils.containsIgnoreCase(type,"script")) {
      obj.remove();
    }
  }
  final Elements embeds=doc.getElementsByTag("embed");
  for (  final Element embed : embeds) {
    final String data=StringUtils.deleteWhitespace(embed.attr("src"));
    if (StringUtils.startsWithIgnoreCase(data,"data:") || StringUtils.startsWithIgnoreCase(data,"javascript")) {
      embed.remove();
    }
  }
  final Elements as=doc.getElementsByTag("a");
  for (  final Element a : as) {
    a.attr("rel","nofollow");
    final String href=a.attr("href");
    if (href.startsWith(Latkes.getServePath())) {
      continue;
    }
    a.attr("target","_blank");
  }
  final Elements audios=doc.getElementsByTag("audio");
  for (  final Element audio : audios) {
    audio.attr("preload","none");
  }
  final Elements videos=doc.getElementsByTag("video");
  for (  final Element video : videos) {
    video.attr("preload","none");
  }
  final Elements forms=doc.getElementsByTag("form");
  for (  final Element form : forms) {
    form.remove();
  }
  final Elements inputs=doc.getElementsByTag("input");
  for (  final Element input : inputs) {
    if (!"checkbox".equalsIgnoreCase(input.attr("type"))) {
      input.remove();
    }
  }
  String ret=doc.body().html();
  ret=ret.replaceAll("(</?br\\s*/?>\\s*)+","<br>");
  return ret;
}
