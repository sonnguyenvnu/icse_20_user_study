/** 
 * Reset the calendar setting all the fields provided to zero.
 */
private void reset(Calendar calendar,List<Integer> fields){
  for (  int field : fields) {
    calendar.set(field,field == Calendar.DAY_OF_MONTH ? 1 : 0);
  }
}
