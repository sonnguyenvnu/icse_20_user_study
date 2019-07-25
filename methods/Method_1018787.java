public long size(){
  if (size == 0 && children.size() != 0)   for (  F child : children.values())   size+=child.size();
  return size;
}
