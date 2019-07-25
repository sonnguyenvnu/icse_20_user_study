/** 
 * Use this method to control browser from outer world.
 * @param url     Full site address.
 * @param headers Can be null.
 * @return Info about loaded page.
 */
public Tab load(String url,Map<String,String> headers){
  if (delayLoad(url,headers)) {
    return null;
  }
  mFactory.setNextHeaders(headers);
  Tab tab=createNewTab(false,true,false);
  loadUrl(tab,url,headers);
  return tab;
}
