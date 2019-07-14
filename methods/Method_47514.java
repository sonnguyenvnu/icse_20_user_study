public void restartWithFade(Class<?> cls){
  new Handler().postDelayed(() -> {
    finish();
    overridePendingTransition(fade_in,fade_out);
    startActivity(new Intent(this,cls));
  }
,500);
}
