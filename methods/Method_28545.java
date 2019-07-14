@NonNull private static String getParsedHtml(@NonNull String source,String owner,String repoName,String builder,String baseLinkUrl,boolean isWiki){
  Document document=Jsoup.parse(source,"");
  Elements imageElements=document.getElementsByTag("img");
  if (imageElements != null && !imageElements.isEmpty()) {
    for (    Element element : imageElements) {
      String src=element.attr("src");
      if (src != null && !(src.startsWith("http://") || src.startsWith("https://"))) {
        String finalSrc;
        if (src.startsWith("/" + owner + "/" + repoName)) {
          finalSrc="https://raw.githubusercontent.com/" + src;
        }
 else {
          finalSrc="https://raw.githubusercontent.com/" + builder + src;
        }
        element.attr("src",finalSrc);
      }
    }
  }
  Elements linkElements=document.getElementsByTag("a");
  if (linkElements != null && !linkElements.isEmpty()) {
    for (    Element element : linkElements) {
      String href=element.attr("href");
      if (href.startsWith("#") || href.startsWith("http://") || href.startsWith("https://") || href.startsWith("mailto:")) {
        continue;
      }
      element.attr("href",baseLinkUrl + (isWiki && href.startsWith("wiki") ? href.replaceFirst("wiki","") : href));
    }
  }
  return document.html();
}
