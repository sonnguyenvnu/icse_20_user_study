protected boolean useSimpleQueryProcessor(BaseVertexCentricQuery query,InternalVertex... vertices){
  assert vertices.length > 0;
  if (!query.isSimple())   return false;
  if (queryOnlyLoaded)   return true;
  for (  InternalVertex vertex : vertices)   if (!vertex.isLoaded())   return false;
  return true;
}
