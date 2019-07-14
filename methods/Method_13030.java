protected DelegatedContainer getDelegatedContainer(Container container){
  Entry root=container.getRoot();
  URI uri=(root == null) ? DEFAULT_ROOT_URI : root.getUri();
  DelegatedContainer delegatedContainer=uriToDelegatedContainer.get(uri);
  if (delegatedContainer == null) {
    uriToDelegatedContainer.put(uri,delegatedContainer=new DelegatedContainer(container));
  }
  return delegatedContainer;
}
