public Map<JanusGraphVertex,Iterable<? extends JanusGraphRelation>> executeImplicitKeyQuery(){
  return new HashMap<JanusGraphVertex,Iterable<? extends JanusGraphRelation>>(vertices.size()){
{
      for (      InternalVertex v : vertices)       put(v,executeImplicitKeyQuery(v));
    }
  }
;
}
