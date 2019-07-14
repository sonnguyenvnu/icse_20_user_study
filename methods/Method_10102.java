/** 
 * Generates index for the specified sitemap.
 * @param sitemap the specified sitemap
 */
public void genIndex(final Sitemap sitemap){
  final Sitemap.URL url=new Sitemap.URL();
  url.setLoc(Latkes.getServePath());
  url.setChangeFreq("always");
  url.setPriority("1.0");
  sitemap.addURL(url);
}
