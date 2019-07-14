private ResourceServerTokenServices resolveTokenServices(){
  if (tokenServices == null || tokenServices.size() == 0) {
    return null;
  }
  if (tokenServices.size() == 1) {
    return tokenServices.values().iterator().next();
  }
  if (tokenServices.size() == 2) {
    Iterator<ResourceServerTokenServices> iter=tokenServices.values().iterator();
    ResourceServerTokenServices one=iter.next();
    ResourceServerTokenServices two=iter.next();
    if (elementsEqual(one,two)) {
      return one;
    }
  }
  return context.getBean(ResourceServerTokenServices.class);
}
