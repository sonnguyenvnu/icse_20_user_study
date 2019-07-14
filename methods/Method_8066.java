public void setEmojiSuggestion(DataQuery.KeywordResult suggestion){
  imageView.setVisibility(INVISIBLE);
  usernameTextView.setVisibility(INVISIBLE);
  StringBuilder stringBuilder=new StringBuilder(suggestion.emoji.length() + suggestion.keyword.length() + 4);
  stringBuilder.append(suggestion.emoji);
  stringBuilder.append("   :");
  stringBuilder.append(suggestion.keyword);
  nameTextView.setText(Emoji.replaceEmoji(stringBuilder,nameTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false));
}
