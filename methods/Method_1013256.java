public void put(SymbolNode nd,Document doc){
  Integer k=new Integer(nd.myUID);
  if (!keys.contains(k)) {
    keys.add(k);
    setTop_level_entry();
    context.put(k,nd.exportDefinition(doc,this));
  }
}
