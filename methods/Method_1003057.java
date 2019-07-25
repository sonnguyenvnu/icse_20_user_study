@Override public int update(){
switch (type) {
case CommandInterface.SET_AUTOCOMMIT_TRUE:
    session.setAutoCommit(true);
  break;
case CommandInterface.SET_AUTOCOMMIT_FALSE:
session.setAutoCommit(false);
break;
case CommandInterface.BEGIN:
session.begin();
break;
case CommandInterface.COMMIT:
session.commit(false);
break;
case CommandInterface.ROLLBACK:
session.rollback();
break;
case CommandInterface.CHECKPOINT:
session.getUser().checkAdmin();
session.getDatabase().checkpoint();
break;
case CommandInterface.SAVEPOINT:
session.addSavepoint(savepointName);
break;
case CommandInterface.ROLLBACK_TO_SAVEPOINT:
session.rollbackToSavepoint(savepointName);
break;
case CommandInterface.CHECKPOINT_SYNC:
session.getUser().checkAdmin();
session.getDatabase().sync();
break;
case CommandInterface.PREPARE_COMMIT:
session.prepareCommit(transactionName);
break;
case CommandInterface.COMMIT_TRANSACTION:
session.getUser().checkAdmin();
session.setPreparedTransaction(transactionName,true);
break;
case CommandInterface.ROLLBACK_TRANSACTION:
session.getUser().checkAdmin();
session.setPreparedTransaction(transactionName,false);
break;
case CommandInterface.SHUTDOWN_IMMEDIATELY:
session.getUser().checkAdmin();
session.getDatabase().shutdownImmediately();
break;
case CommandInterface.SHUTDOWN:
case CommandInterface.SHUTDOWN_COMPACT:
case CommandInterface.SHUTDOWN_DEFRAG:
{
session.getUser().checkAdmin();
session.commit(false);
session.throttle();
Database db=session.getDatabase();
if (db.setExclusiveSession(session,true)) {
if (type == CommandInterface.SHUTDOWN_COMPACT || type == CommandInterface.SHUTDOWN_DEFRAG) {
db.setCompactMode(type);
}
db.setCloseDelay(0);
session.close();
}
break;
}
default :
DbException.throwInternalError("type=" + type);
}
return 0;
}
