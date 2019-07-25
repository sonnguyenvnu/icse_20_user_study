@Override public Iterable<Problem> check(SModule m){
  return ListSequence.fromList(findAffectedVariables(m)).concat(ListSequence.fromList(findAffectedAntiquotations(m))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return (AttributeOperations.getAttribute(it,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x78c7e79625a38e06L,"jetbrains.mps.lang.core.structure.ReviewMigration"))) == null);
    }
  }
).select(new ISelector<SNode,Problem>(){
    public Problem select(    SNode it){
      Problem migrated=new NotMigratedNode(it){
        public String getMessage(){
          return "usages that was affected be semantics change";
        }
      }
;
      return migrated;
    }
  }
);
}
