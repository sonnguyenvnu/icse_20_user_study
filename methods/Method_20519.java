@SuppressLint("SetTextI18n") private void setupBuildInformationSection(){
  this.buildDate.setText(this.build.dateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss aa zzz")));
  this.sha.setText(this.build.sha());
  this.variant.setText(this.build.variant());
  this.versionCode.setText(this.build.versionCode().toString());
  this.versionName.setText(this.build.versionName());
}
