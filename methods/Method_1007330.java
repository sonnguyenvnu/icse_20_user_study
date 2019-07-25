public Declarator lookup(String name){
  Declarator found=get(name);
  if (found == null && parent != null)   return parent.lookup(name);
  return found;
}
