@Override public void insert(EventModel eventModel){
  EventTbl eventTbl=eventModel.toEventTbl(new Function<String,String>(){
    @Override public String apply(    String s){
      return s;
    }
  }
);
  if (eventTbl.getDataChangeLastTime() == null) {
    eventTbl.setDataChangeLastTime(new Date());
  }
  eventDao.insert(eventTbl);
}
