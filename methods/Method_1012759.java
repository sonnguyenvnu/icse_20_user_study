@Override protected void append(E eventObject){
  try {
synchronized (eventListLock) {
      eventList.add(eventObject);
    }
  }
 catch (  Exception e) {
    addStatus(new ErrorStatus(getName() + " failed to append event: " + e.getLocalizedMessage(),this,e));
  }
}
