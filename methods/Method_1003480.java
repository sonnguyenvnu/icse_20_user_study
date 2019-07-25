private void color(Note note,RemoteViews row){
  String colorsPref=app.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_MULTI_PROCESS).getString("settings_colors_widget",Constants.PREF_COLORS_APP_DEFAULT);
  if (!colorsPref.equals("disabled")) {
    row.setInt(R.id.tag_marker,"setBackgroundColor",Color.parseColor("#00000000"));
    if (note.getCategory() != null && note.getCategory().getColor() != null) {
      if (colorsPref.equals("list")) {
        row.setInt(R.id.card_layout,"setBackgroundColor",Integer.parseInt(note.getCategory().getColor()));
      }
 else {
        row.setInt(R.id.tag_marker,"setBackgroundColor",Integer.parseInt(note.getCategory().getColor()));
      }
    }
 else {
      row.setInt(R.id.tag_marker,"setBackgroundColor",0);
    }
  }
}
