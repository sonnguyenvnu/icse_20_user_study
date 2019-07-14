public long rootId(){
  return isRoot() ? id() : parentId();
}
