@Override public Object get(MfDate when){
  for (  Object o : milestones()) {
    MfDate thisDate=(MfDate)o;
    if (thisDate.before(when) || thisDate.equals(when)) {
      return myContents.get(thisDate);
    }
  }
  throw new IllegalArgumentException("no records that early");
}
