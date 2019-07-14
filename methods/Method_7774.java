private void updateCell(){
  if (sendLocationCell != null) {
    if (customLocation != null) {
      sendLocationCell.setText(LocaleController.getString("SendSelectedLocation",R.string.SendSelectedLocation),String.format(Locale.US,"(%f,%f)",customLocation.getLatitude(),customLocation.getLongitude()));
    }
 else {
      if (gpsLocation != null) {
        sendLocationCell.setText(LocaleController.getString("SendLocation",R.string.SendLocation),LocaleController.formatString("AccurateTo",R.string.AccurateTo,LocaleController.formatPluralString("Meters",(int)gpsLocation.getAccuracy())));
      }
 else {
        sendLocationCell.setText(LocaleController.getString("SendLocation",R.string.SendLocation),LocaleController.getString("Loading",R.string.Loading));
      }
    }
  }
}
