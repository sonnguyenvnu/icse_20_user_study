private void startSettingsActivity(){
  final Intent intent=new Intent(this,SettingsActivity.class);
  startActivity(intent);
  overridePendingTransition(0,0);
}
