public Q holdCursorsOverCommit(){
  setHoldability(QueryHoldability.HOLD_CURSORS_OVER_COMMIT);
  return _this();
}
