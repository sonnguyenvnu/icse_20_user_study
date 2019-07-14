/** 
 * Compare two provider group list, return add list and remove list
 * @param oldGroups old provider group list
 * @param newGroups new provider group list
 * @param add      provider list need add
 * @param remove   provider list need remove
 */
public static void compareGroups(List<ProviderGroup> oldGroups,List<ProviderGroup> newGroups,List<ProviderInfo> add,List<ProviderInfo> remove){
  if (CommonUtils.isEmpty(oldGroups)) {
    if (CommonUtils.isNotEmpty(newGroups)) {
      for (      ProviderGroup newGroup : newGroups) {
        add.addAll(newGroup.getProviderInfos());
      }
    }
  }
 else {
    if (CommonUtils.isEmpty(newGroups)) {
      for (      ProviderGroup oldGroup : oldGroups) {
        remove.addAll(oldGroup.getProviderInfos());
      }
    }
 else {
      if (CommonUtils.isNotEmpty(oldGroups)) {
        Map<String,List<ProviderInfo>> oldMap=convertToMap(oldGroups);
        Map<String,List<ProviderInfo>> mapTmp=convertToMap(newGroups);
        for (        Map.Entry<String,List<ProviderInfo>> oldEntry : oldMap.entrySet()) {
          String key=oldEntry.getKey();
          List<ProviderInfo> oldList=oldEntry.getValue();
          if (mapTmp.containsKey(key)) {
            final List<ProviderInfo> newList=mapTmp.remove(key);
            compareProviders(oldList,newList,add,remove);
            mapTmp.remove(key);
          }
 else {
            remove.addAll(oldList);
          }
        }
        for (        Map.Entry<String,List<ProviderInfo>> entry : mapTmp.entrySet()) {
          add.addAll(entry.getValue());
        }
      }
    }
  }
}
