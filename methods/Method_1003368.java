@Override public Value copy(DataHandler database,int tableId){
  if (small == null) {
    return handler.getLobStorage().copyLob(this,tableId,precision);
  }
 else   if (small.length > database.getMaxLengthInplaceLob()) {
    LobStorageInterface s=database.getLobStorage();
    Value v;
    if (valueType == Value.BLOB) {
      v=s.createBlob(getInputStream(),precision);
    }
 else {
      v=s.createClob(getReader(),precision);
    }
    Value v2=v.copy(database,tableId);
    v.remove();
    return v2;
  }
  return this;
}
