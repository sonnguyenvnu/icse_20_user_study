/** 
 * Encodes the handler lists.
 * @param file {@code non-null;} file this instance is part of
 */
public void encode(DexFile file){
  finishProcessingIfNecessary();
  TypeIdsSection typeIds=file.getTypeIds();
  int size=table.size();
  handlerOffsets=new TreeMap<CatchHandlerList,Integer>();
  for (int i=0; i < size; i++) {
    handlerOffsets.put(table.get(i).getHandlers(),null);
  }
  if (handlerOffsets.size() > 65535) {
    throw new UnsupportedOperationException("too many catch handlers");
  }
  ByteArrayAnnotatedOutput out=new ByteArrayAnnotatedOutput();
  encodedHandlerHeaderSize=out.writeUleb128(handlerOffsets.size());
  for (  Map.Entry<CatchHandlerList,Integer> mapping : handlerOffsets.entrySet()) {
    CatchHandlerList list=mapping.getKey();
    int listSize=list.size();
    boolean catchesAll=list.catchesAll();
    mapping.setValue(out.getCursor());
    if (catchesAll) {
      out.writeSleb128(-(listSize - 1));
      listSize--;
    }
 else {
      out.writeSleb128(listSize);
    }
    for (int i=0; i < listSize; i++) {
      CatchHandlerList.Entry entry=list.get(i);
      out.writeUleb128(typeIds.indexOf(entry.getExceptionType()));
      out.writeUleb128(entry.getHandler());
    }
    if (catchesAll) {
      out.writeUleb128(list.get(listSize).getHandler());
    }
  }
  encodedHandlers=out.toByteArray();
}
