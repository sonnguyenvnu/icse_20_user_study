/** 
 * This method adds <pap, level> into this set, and "subsumes" it with another one for the same parameter if one is there, or ignoring the constraint if it is vacuous.
 */
@Override public final Integer put(ParamAndPosition pap,Integer level){
  int newLevel=level.intValue();
  Integer old=this.get(pap);
  int oldLevel=(old == null) ? MinLevel : old.intValue();
  super.put(pap,new Integer(Math.max(newLevel,oldLevel)));
  return old;
}
