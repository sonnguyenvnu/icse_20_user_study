public boolean isBannedInline(){
  return foundContextBot != null && !inlineMediaEnabled;
}
