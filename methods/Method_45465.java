/** 
 * Compare two provider list, return add list and remove list
 * @param oldList old Provider list
 * @param newList new provider list
 * @param add     provider list need add
 * @param remove  provider list need remove
 */
public static void compareProviders(List<ProviderInfo> oldList,List<ProviderInfo> newList,List<ProviderInfo> add,List<ProviderInfo> remove){
  if (CommonUtils.isEmpty(oldList)) {
    if (CommonUtils.isNotEmpty(newList)) {
      add.addAll(newList);
    }
  }
 else {
    if (CommonUtils.isEmpty(newList)) {
      remove.addAll(oldList);
    }
 else {
      if (CommonUtils.isNotEmpty(oldList)) {
        List<ProviderInfo> tmpList=new ArrayList<ProviderInfo>(newList);
        for (        ProviderInfo oldProvider : oldList) {
          if (tmpList.contains(oldProvider)) {
            tmpList.remove(oldProvider);
          }
 else {
            remove.add(oldProvider);
          }
        }
        add.addAll(tmpList);
      }
    }
  }
}
