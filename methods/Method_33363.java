void updateValue(){
  if (is24HourView) {
    LocalTimeStringConverter localTimeStringConverter=new LocalTimeStringConverter(FormatStyle.SHORT,Locale.GERMAN);
    timePicker.setValue(localTimeStringConverter.fromString(selectedHourLabel.getText() + ":" + selectedMinLabel.getText()));
  }
 else {
    timePicker.setValue(LocalTime.parse(selectedHourLabel.getText() + ":" + selectedMinLabel.getText() + " " + period.get(),DateTimeFormatter.ofPattern("h:mm a").withLocale(Locale.ENGLISH)));
  }
}
