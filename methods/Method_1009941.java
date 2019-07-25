/** 
 * ??????????
 */
public void check(){
  Set<String> set=new HashSet<>();
  for (  Map.Entry<String,IComponentHostRouter> entry : hostRouterMap.entrySet()) {
    IComponentHostRouter childRouter=entry.getValue();
    if (childRouter == null || childRouter.getRouterMap() == null) {
      continue;
    }
    Map<String,RouterBean> childRouterMap=childRouter.getRouterMap();
    for (    String key : childRouterMap.keySet()) {
      if (set.contains(key)) {
        throw new IllegalStateException("the target uri is exist?" + key);
      }
      set.add(key);
    }
  }
}
