public final void put(UniqueString key,Context ctxt,ModuleNode moduleNode){
  ExternalModuleTableEntry c=moduleHashTable.get(key);
  if (c == null) {
    moduleHashTable.put(key,new ExternalModuleTableEntry(ctxt,moduleNode));
    moduleNodeVector.addElement(moduleNode);
  }
}
