private void readInlineTypes(long[] keyIds,LongObjectHashMap properties,ReadBuffer in,TypeInspector tx,InlineType inlineType){
  for (  long keyId : keyIds) {
    PropertyKey keyType=tx.getExistingPropertyKey(keyId);
    Object value=readInline(in,keyType,inlineType);
    if (value != null)     properties.put(keyId,value);
  }
}
