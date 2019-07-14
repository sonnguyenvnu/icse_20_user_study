/** 
 * A simple method to download a url.
 * @param url url
 * @param charset charset
 * @return html
 */
public Html download(String url,String charset){
  Page page=download(new Request(url),Site.me().setCharset(charset).toTask());
  return (Html)page.getHtml();
}
