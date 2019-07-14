public Q closeCursorsAtCommit(){
  setHoldability(QueryHoldability.CLOSE_CURSORS_AT_COMMIT);
  return _this();
}
