private void writeInlineTypes(long[] keyIds,InternalRelation relation,DataOutput out,TypeInspector tx,InlineType inlineType){
  for (  long keyId : keyIds) {
    PropertyKey t=tx.getExistingPropertyKey(keyId);
    writeInline(out,t,relation.getValueDirect(t),inlineType);
  }
}
