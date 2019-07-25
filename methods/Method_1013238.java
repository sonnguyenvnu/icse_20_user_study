/** 
 * Returns a duplicate of this Context.  Called once from SymbolTable class.  The tricky part is duplicating the linked-list of Pairs starting from this.lastpair.
 */
public Context duplicate(ExternalModuleTable exMT){
  Context dup=new Context(exMT,errors);
  Pair p=this.lastPair;
  Pair current=null;
  boolean firstTime=true;
  while (p != null) {
    if (firstTime) {
      current=new Pair(null,p.info);
      dup.lastPair=current;
      firstTime=false;
    }
 else {
      current.link=new Pair(null,p.info);
      current=current.link;
    }
    dup.table.put(current.info.getName(),current);
    p=p.link;
  }
  return dup;
}
