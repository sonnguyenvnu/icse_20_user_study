@Override public Map<JanusGraphVertex,Iterable<JanusGraphRelation>> relations(){
  return (Map)(isImplicitKeyQuery(RelationCategory.RELATION) ? executeImplicitKeyQuery() : execute(RelationCategory.RELATION,new RelationConstructor()));
}
