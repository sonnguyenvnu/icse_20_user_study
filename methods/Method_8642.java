private void updateEmojiTabs(){
  int newHas=Emoji.recentEmoji.isEmpty() ? 0 : 1;
  if (hasRecentEmoji != -1 && hasRecentEmoji == newHas) {
    return;
  }
  hasRecentEmoji=newHas;
  emojiTabs.removeTabs();
  String[] descriptions={LocaleController.getString("RecentStickers",R.string.RecentStickers),LocaleController.getString("Emoji1",R.string.Emoji1),LocaleController.getString("Emoji2",R.string.Emoji2),LocaleController.getString("Emoji3",R.string.Emoji3),LocaleController.getString("Emoji4",R.string.Emoji4),LocaleController.getString("Emoji5",R.string.Emoji5),LocaleController.getString("Emoji6",R.string.Emoji6),LocaleController.getString("Emoji7",R.string.Emoji7),LocaleController.getString("Emoji8",R.string.Emoji8)};
  for (int a=0; a < emojiIcons.length; a++) {
    if (a == 0 && Emoji.recentEmoji.isEmpty()) {
      continue;
    }
    emojiTabs.addIconTab(emojiIcons[a]).setContentDescription(descriptions[a]);
  }
  emojiTabs.updateTabStyles();
}
