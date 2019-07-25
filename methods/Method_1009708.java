protected SharedPreferences open(){
  return context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
}
