public String applyBackground(){
  String currentTag=(String)getTag();
  String backgroundKey=Theme.hasThemeKey(Theme.key_chats_menuTopBackground) && Theme.getColor(Theme.key_chats_menuTopBackground) != 0 ? Theme.key_chats_menuTopBackground : Theme.key_chats_menuTopBackgroundCats;
  if (!backgroundKey.equals(currentTag)) {
    setBackgroundColor(Theme.getColor(backgroundKey));
    setTag(backgroundKey);
  }
  return backgroundKey;
}
