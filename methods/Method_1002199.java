public void restart(){
  Intent intent=new Intent();
  intent.setClass(this,BootstrapActivity.class);
  intent.putExtra(BootstrapActivity.SKIP_RESTORE,true);
  startActivity(intent);
  System.exit(0);
}
