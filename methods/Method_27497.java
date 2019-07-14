@Override public void onThemeChanged(){
  if (this instanceof MainActivity) {
    recreate();
  }
 else {
    Intent intent=new Intent(this,MainActivity.class);
    intent.putExtras(Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).end());
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }
}
