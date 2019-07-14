@Deprecated public boolean fixDate(){
  long newDate=getDateTaken();
  if (newDate != -1) {
    File f=new File(path);
    if (f.setLastModified(newDate)) {
      dateModified=newDate;
      return true;
    }
  }
  return false;
}
