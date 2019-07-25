/** 
 * This method adds <param, level> into this map. It subsumes any existing one. 
 */
@Override public final Integer put(SymbolNode param,Integer level){
  int newLevel=level.intValue();
  Integer old=this.get(param);
  int oldLevel=(old == null) ? MaxLevel : old.intValue();
  super.put(param,new Integer(Math.min(newLevel,oldLevel)));
  return old;
}
