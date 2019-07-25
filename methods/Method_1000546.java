private AopConfigrationItem parse(Element item){
  AopConfigrationItem aopItem=new AopConfigrationItem();
  aopItem.setClassName(item.getAttribute("name"));
  aopItem.setMethodName(item.getAttribute("method"));
  aopItem.setInterceptor(item.getAttribute("interceptor"));
  if (item.hasAttribute("singleton"))   aopItem.setSingleton(Boolean.parseBoolean(item.getAttribute("singleton")));
  return aopItem;
}
