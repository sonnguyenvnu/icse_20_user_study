/** 
 * We need to implement this method to properly find things to delegate to: it cannot be done earlier since delegated deserializers almost certainly require access to this instance (at least "List" and "Map" ones)
 */
@SuppressWarnings("unchecked") @Override public void resolve(DeserializationContext ctxt) throws JsonMappingException {
  JavaType obType=ctxt.constructType(Object.class);
  JavaType stringType=ctxt.constructType(String.class);
  TypeFactory tf=ctxt.getTypeFactory();
  if (_listType == null) {
    _listDeserializer=_clearIfStdImpl(_findCustomDeser(ctxt,tf.constructCollectionType(List.class,obType)));
  }
 else {
    _listDeserializer=_findCustomDeser(ctxt,_listType);
  }
  if (_mapType == null) {
    _mapDeserializer=_clearIfStdImpl(_findCustomDeser(ctxt,tf.constructMapType(Map.class,stringType,obType)));
  }
 else {
    _mapDeserializer=_findCustomDeser(ctxt,_mapType);
  }
  _stringDeserializer=_clearIfStdImpl(_findCustomDeser(ctxt,stringType));
  _numberDeserializer=_clearIfStdImpl(_findCustomDeser(ctxt,tf.constructType(Number.class)));
  JavaType unknown=TypeFactory.unknownType();
  _mapDeserializer=(JsonDeserializer<Object>)ctxt.handleSecondaryContextualization(_mapDeserializer,null,unknown);
  _listDeserializer=(JsonDeserializer<Object>)ctxt.handleSecondaryContextualization(_listDeserializer,null,unknown);
  _stringDeserializer=(JsonDeserializer<Object>)ctxt.handleSecondaryContextualization(_stringDeserializer,null,unknown);
  _numberDeserializer=(JsonDeserializer<Object>)ctxt.handleSecondaryContextualization(_numberDeserializer,null,unknown);
}
