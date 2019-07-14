/** 
 * Compare two provider group, return add list and remove list
 * @param oldGroup old provider group
 * @param newGroup new provider group
 * @param add      provider list need add
 * @param remove   provider list need remove
 */
public static void compareGroup(ProviderGroup oldGroup,ProviderGroup newGroup,List<ProviderInfo> add,List<ProviderInfo> remove){
  compareProviders(oldGroup.getProviderInfos(),newGroup.getProviderInfos(),add,remove);
}
