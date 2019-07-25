public boolean contains(String key){
  SharedPreferences sharePre=ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
  return sharePre.contains(key);
}
