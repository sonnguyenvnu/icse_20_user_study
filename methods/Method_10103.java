/** 
 * Generates domains for the specified sitemap.
 * @param sitemap the specified sitemap
 */
public void genDomains(final Sitemap sitemap){
  final List<JSONObject> domains=domainCache.getDomains(Integer.MAX_VALUE);
  for (  final JSONObject domain : domains) {
    final String permalink=Latkes.getServePath() + "/domain/" + domain.optString(Domain.DOMAIN_URI);
    final Sitemap.URL url=new Sitemap.URL();
    url.setLoc(permalink);
    url.setChangeFreq("always");
    url.setPriority("0.9");
    sitemap.addURL(url);
  }
}
