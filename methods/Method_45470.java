/** 
 * ?URL???Provider
 * @param url url??
 * @return Provider?? provider
 * @deprecated use {@link ProviderHelper#toProviderInfo(String)}
 */
@Deprecated public static ProviderInfo valueOf(String url){
  return ProviderHelper.toProviderInfo(url);
}
