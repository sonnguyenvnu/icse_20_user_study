private void reportUnresolvedModule(){
  addError("module not found: " + name.getId());
  Indexer.idx.recordUnresolvedModule(thisQname(),getFile());
}
