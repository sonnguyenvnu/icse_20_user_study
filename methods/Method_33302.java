private DateCell createDayCell(){
  DateCell dayCell=null;
  if (datePicker.getDayCellFactory() != null) {
    dayCell=datePicker.getDayCellFactory().call(datePicker);
  }
  if (dayCell == null) {
    dayCell=new DateCell();
  }
  return dayCell;
}
