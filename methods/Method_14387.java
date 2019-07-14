static public void registerFormat(String format,String label,String uiClass,ImportingParser parser){
  formatToRecord.put(format,new Format(format,label,true,uiClass,parser));
}
