@OnClick(R.id.exitOptionItemView) void exit(){
  ChatManagerHolder.gChatManager.disconnect(true);
  SharedPreferences sp=getSharedPreferences("config",Context.MODE_PRIVATE);
  sp.edit().clear().apply();
  Intent intent=new Intent(this,SplashActivity.class);
  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
  startActivity(intent);
  finish();
}
