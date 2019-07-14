private static RelationIdentifier bytebuffer2RelationId(ReadBuffer b){
  long[] relationId=new long[4];
  for (int i=0; i < 3; i++)   relationId[i]=VariableLong.readPositive(b);
  if (b.hasRemaining())   relationId[3]=VariableLong.readPositive(b);
 else   relationId=Arrays.copyOfRange(relationId,0,3);
  return RelationIdentifier.get(relationId);
}
