public static InternalRelation readRelation(final InternalVertex vertex,final Entry data,final EdgeSerializer serializer,final TypeInspector types,final VertexFactory vertexFac){
  RelationCache relation=serializer.readRelation(data,true,types);
  return readRelation(vertex,relation,data,types,vertexFac);
}
