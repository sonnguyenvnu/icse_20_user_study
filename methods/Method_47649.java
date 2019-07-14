@Nullable private Timestamp positionToTimestamp(float x,float y){
  int col=(int)(x / columnWidth);
  int row=(int)(y / columnWidth);
  if (row == 0)   return null;
  if (col == nColumns - 1)   return null;
  int offset=col * 7 + (row - 1);
  Calendar date=(Calendar)baseDate.clone();
  date.add(Calendar.DAY_OF_YEAR,offset);
  if (DateUtils.getStartOfDay(date.getTimeInMillis()) > DateUtils.getStartOfToday())   return null;
  return new Timestamp(date.getTimeInMillis());
}
