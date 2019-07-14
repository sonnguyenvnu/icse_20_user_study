@Override public Iterable<JanusGraphRelation> relations(){
  return (Iterable)(isImplicitKeyQuery(RelationCategory.RELATION) ? executeImplicitKeyQuery(vertex) : execute(RelationCategory.RELATION,new RelationConstructor()));
}
