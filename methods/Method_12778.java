public static void setSoVersion(Context context,String name,int version){
  SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
  SharedPreferences.Editor editor=preferences.edit();
  editor.putInt(name,version);
  editor.commit();
}
