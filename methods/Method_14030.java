static public Mode stringToMode(String s){
  return MODE_ROW_BASED.equals(s) ? Mode.RowBased : Mode.RecordBased;
}
