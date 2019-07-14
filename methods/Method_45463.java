/** 
 * ????????
 * @param providerInfos ??????????
 * @return ?????? provider group
 */
public ProviderGroup addAll(Collection<ProviderInfo> providerInfos){
  if (CommonUtils.isEmpty(providerInfos)) {
    return this;
  }
  ConcurrentHashSet<ProviderInfo> tmp=new ConcurrentHashSet<ProviderInfo>(this.providerInfos);
  tmp.addAll(providerInfos);
  this.providerInfos=new ArrayList<ProviderInfo>(tmp);
  return this;
}
