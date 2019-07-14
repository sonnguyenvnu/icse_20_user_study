/** 
 * Modified from  {@link Linkify#addLinks(Spannable,int)}.
 */
public static Spannable addLinks(Spannable spannable){
  List<Link> links=new ArrayList<>();
  gatherLinks(links,spannable,WEB_URL_PATTERN,WEB_URL_SCHEMES,WEB_URL_MATCH_FILTER);
  gatherLinks(links,spannable,EMAIL_PATTERN,EMAIL_SCHEMES,null);
  gatherLinks(links,spannable,PHONE_URI_PATTERN,null,null);
  pruneOverlaps(links);
  if (links.size() == 0) {
    return spannable;
  }
  for (  Link link : links) {
    applyLink(link,spannable);
  }
  return spannable;
}
