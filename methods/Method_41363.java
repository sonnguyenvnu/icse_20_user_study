/** 
 * <p> Select a trigger. </p>
 * @param conn the DB Connection
 * @return the <code>{@link org.quartz.Trigger}</code> object
 * @throws JobPersistenceException 
 */
public OperableTrigger selectTrigger(Connection conn,TriggerKey triggerKey) throws SQLException, ClassNotFoundException, IOException, JobPersistenceException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    OperableTrigger trigger=null;
    ps=conn.prepareStatement(rtp(SELECT_TRIGGER));
    ps.setString(1,triggerKey.getName());
    ps.setString(2,triggerKey.getGroup());
    rs=ps.executeQuery();
    if (rs.next()) {
      String jobName=rs.getString(COL_JOB_NAME);
      String jobGroup=rs.getString(COL_JOB_GROUP);
      String description=rs.getString(COL_DESCRIPTION);
      long nextFireTime=rs.getLong(COL_NEXT_FIRE_TIME);
      long prevFireTime=rs.getLong(COL_PREV_FIRE_TIME);
      String triggerType=rs.getString(COL_TRIGGER_TYPE);
      long startTime=rs.getLong(COL_START_TIME);
      long endTime=rs.getLong(COL_END_TIME);
      String calendarName=rs.getString(COL_CALENDAR_NAME);
      int misFireInstr=rs.getInt(COL_MISFIRE_INSTRUCTION);
      int priority=rs.getInt(COL_PRIORITY);
      Map<?,?> map=null;
      if (canUseProperties()) {
        map=getMapFromProperties(rs);
      }
 else {
        map=(Map<?,?>)getObjectFromBlob(rs,COL_JOB_DATAMAP);
      }
      Date nft=null;
      if (nextFireTime > 0) {
        nft=new Date(nextFireTime);
      }
      Date pft=null;
      if (prevFireTime > 0) {
        pft=new Date(prevFireTime);
      }
      Date startTimeD=new Date(startTime);
      Date endTimeD=null;
      if (endTime > 0) {
        endTimeD=new Date(endTime);
      }
      if (triggerType.equals(TTYPE_BLOB)) {
        rs.close();
        rs=null;
        ps.close();
        ps=null;
        ps=conn.prepareStatement(rtp(SELECT_BLOB_TRIGGER));
        ps.setString(1,triggerKey.getName());
        ps.setString(2,triggerKey.getGroup());
        rs=ps.executeQuery();
        if (rs.next()) {
          trigger=(OperableTrigger)getObjectFromBlob(rs,COL_BLOB);
        }
      }
 else {
        TriggerPersistenceDelegate tDel=findTriggerPersistenceDelegate(triggerType);
        if (tDel == null)         throw new JobPersistenceException("No TriggerPersistenceDelegate for trigger discriminator type: " + triggerType);
        TriggerPropertyBundle triggerProps=null;
        try {
          triggerProps=tDel.loadExtendedTriggerProperties(conn,triggerKey);
        }
 catch (        IllegalStateException isex) {
          if (isTriggerStillPresent(ps)) {
            throw isex;
          }
 else {
            return null;
          }
        }
        TriggerBuilder<?> tb=newTrigger().withDescription(description).withPriority(priority).startAt(startTimeD).endAt(endTimeD).withIdentity(triggerKey).modifiedByCalendar(calendarName).withSchedule(triggerProps.getScheduleBuilder()).forJob(jobKey(jobName,jobGroup));
        if (null != map) {
          tb.usingJobData(new JobDataMap(map));
        }
        trigger=(OperableTrigger)tb.build();
        trigger.setMisfireInstruction(misFireInstr);
        trigger.setNextFireTime(nft);
        trigger.setPreviousFireTime(pft);
        setTriggerStateProperties(trigger,triggerProps);
      }
    }
    return trigger;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
