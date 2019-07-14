@Override protected int getThemeColor(String key){
switch (key) {
case Theme.key_dialogBackground:
    return 0xFF262626;
case Theme.key_dialogTextBlack:
case Theme.key_dialogButton:
case Theme.key_dialogScrollGlow:
  return 0xFFFFFFFF;
}
return super.getThemeColor(key);
}
