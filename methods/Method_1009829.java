private void open(){
  Intent intent=new Intent(this,this.getClass());
  intent.putExtra(LaunchModeActivity.KEY_LABEL,mLabel);
  intent.putExtra(LaunchModeActivity.KEY_INDEX,mNext);
  startActivity(intent);
}
