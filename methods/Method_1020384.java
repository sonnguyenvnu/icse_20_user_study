/** 
 * @param sources old register list
 * @return new mapped register list, or old if nothing has changed.
 */
public final RegisterSpecList map(RegisterSpecList sources){
  int sz=sources.size();
  RegisterSpecList newSources=new RegisterSpecList(sz);
  for (int i=0; i < sz; i++) {
    newSources.set(i,map(sources.get(i)));
  }
  newSources.setImmutable();
  return newSources.equals(sources) ? sources : newSources;
}
