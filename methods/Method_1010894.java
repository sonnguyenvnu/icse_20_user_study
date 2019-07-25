public static SNode construct(SNode base,StringTokenizer tokenizer){
  SNode curr=base;
  while ((curr != null) && tokenizer.hasMoreTokens()) {
    final String tok=tokenizer.nextToken();
    curr=Sequence.fromIterable(getImmediateNestedClassifiers(curr)).findFirst(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return tok.equals(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")));
      }
    }
);
  }
  return curr;
}
