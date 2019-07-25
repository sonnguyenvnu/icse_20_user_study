/** 
 * For each element of supplied collection, take child from specified role (if any), and return these as a sequence. Result sequence doesn't contain null values. Null elements in the source collections are tolerated (and ignored)
 */
public static Iterable<SNode> collect(Iterable<SNode> collection,final SContainmentLink l){
  return Sequence.fromIterable(collection).select(new ISelector<SNode,SNode>(){
    public SNode select(    SNode it){
      return getTarget(it,l);
    }
  }
).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return it != null;
    }
  }
);
}
