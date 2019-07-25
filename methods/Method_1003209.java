/** 
 * Call the trigger class if required. This method does nothing if the trigger is not defined for the given action. This method is called before or after any rows have been processed, once for each statement.
 * @param session the session
 * @param type the trigger type
 * @param beforeAction if this method is called before applying the changes
 */
public void fire(Session session,int type,boolean beforeAction){
  if (rowBased || before != beforeAction || (typeMask & type) == 0) {
    return;
  }
  load();
  Connection c2=session.createConnection(false);
  boolean old=false;
  if (type != Trigger.SELECT) {
    old=session.setCommitOrRollbackDisabled(true);
  }
  Value identity=session.getLastScopeIdentity();
  try {
    triggerCallback.fire(c2,null,null);
  }
 catch (  Throwable e) {
    throw getErrorExecutingTrigger(e);
  }
 finally {
    if (session.getLastTriggerIdentity() != null) {
      session.setLastScopeIdentity(session.getLastTriggerIdentity());
      session.setLastTriggerIdentity(null);
    }
 else {
      session.setLastScopeIdentity(identity);
    }
    if (type != Trigger.SELECT) {
      session.setCommitOrRollbackDisabled(old);
    }
  }
}
