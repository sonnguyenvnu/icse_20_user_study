static public void registerFormat(String format,String label,boolean download,String uiClass,ImportingParser parser){
  formatToRecord.put(format,new Format(format,label,download,uiClass,parser));
}
