private String replace(String text){
  return text.replaceAll("&nbsp;","\u00A0").replaceAll("&amp;","&").replaceAll("&quot;","\"").replaceAll("&cent;","¢").replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("&sect;","§").replaceAll("&ldquo;","“").replaceAll("&rdquo;","”").replaceAll("&lsquo;","‘").replaceAll("&rsquo;","’").replaceAll("&ndash;","\u2013").replaceAll("&mdash;","\u2014").replaceAll("&horbar;","\u2015");
}
