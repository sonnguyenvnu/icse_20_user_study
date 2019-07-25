@Nullable public SNode resolve(SNode contextNode,@NotNull final String refText){
  Iterable<SNode> candidates=Sequence.fromIterable(getAllStaticFields()).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return refText.equals(getReferenceText(it));
    }
  }
);
  if (Sequence.fromIterable(candidates).count() == 1) {
    return Sequence.fromIterable(candidates).first();
  }
  return null;
}
