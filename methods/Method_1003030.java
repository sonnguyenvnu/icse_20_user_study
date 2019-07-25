@Override public int update(){
  session.commit(true);
  Database db=session.getDatabase();
  if (getSchema().findTrigger(triggerName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.TRIGGER_ALREADY_EXISTS_1,triggerName);
  }
  if ((typeMask & Trigger.SELECT) == Trigger.SELECT && rowBased) {
    throw DbException.get(ErrorCode.TRIGGER_SELECT_AND_ROW_BASED_NOT_SUPPORTED,triggerName);
  }
  int id=getObjectId();
  Table table=getSchema().getTableOrView(session,tableName);
  TriggerObject trigger=new TriggerObject(getSchema(),id,triggerName,table);
  trigger.setInsteadOf(insteadOf);
  trigger.setBefore(before);
  trigger.setNoWait(noWait);
  trigger.setQueueSize(queueSize);
  trigger.setRowBased(rowBased);
  trigger.setTypeMask(typeMask);
  trigger.setOnRollback(onRollback);
  if (this.triggerClassName != null) {
    trigger.setTriggerClassName(triggerClassName,force);
  }
 else {
    trigger.setTriggerSource(triggerSource,force);
  }
  db.addSchemaObject(session,trigger);
  table.addTrigger(trigger);
  return 0;
}
