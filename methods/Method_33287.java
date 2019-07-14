private void updateDayNameCells(){
  int weekFirstDay=WeekFields.of(getLocale()).getFirstDayOfWeek().getValue();
  LocalDate date=LocalDate.of(2009,7,12 + weekFirstDay);
  for (int i=0; i < daysPerWeek; i++) {
    String name=weekDayNameFormatter.withLocale(getLocale()).format(date.plus(i,DAYS));
    if (weekDayNameFormatter.getLocale() == java.util.Locale.CHINA) {
      name=name.substring(name.length() - 1).toUpperCase();
    }
 else {
      name=name.substring(0,1).toUpperCase();
    }
    weekDaysCells.get(i).setText(name);
  }
}
