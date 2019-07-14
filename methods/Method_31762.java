private DateTime getADate(String s){
  DateTime retDT=null;
  try {
    retDT=new DateTime(s);
  }
 catch (  IllegalArgumentException pe) {
  }
  return retDT;
}
