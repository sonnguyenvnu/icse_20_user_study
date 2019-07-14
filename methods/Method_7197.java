public static void setSuggestStickers(int type){
  suggestStickers=type;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putInt("suggestStickers",suggestStickers);
  editor.commit();
}
