public void toMain(){
  Intent intent=new Intent();
  intent.setClass(context,ActivityMain.class);
  startActivity(intent);
  finish();
}
