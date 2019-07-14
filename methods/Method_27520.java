public void onRestartApp(){
  Intent intent=new Intent(this,MainActivity.class);
  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
  startActivity(intent);
  finishAndRemoveTask();
}
