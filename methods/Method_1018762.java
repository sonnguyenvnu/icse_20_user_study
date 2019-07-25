final void terminate(int driverErrorCode,String message,Throwable throwable) throws SQLServerException {
  String state=this.state.equals(State.Opened) ? SQLServerException.EXCEPTION_XOPEN_CONNECTION_FAILURE : SQLServerException.EXCEPTION_XOPEN_CONNECTION_CANT_ESTABLISH;
  if (!xopenStates)   state=SQLServerException.mapFromXopen(state);
  SQLServerException ex=new SQLServerException(this,SQLServerException.checkAndAppendClientConnId(message,this),state,0,true);
  if (null != throwable)   ex.initCause(throwable);
  ex.setDriverErrorCode(driverErrorCode);
  notifyPooledConnection(ex);
  close();
  throw ex;
}
