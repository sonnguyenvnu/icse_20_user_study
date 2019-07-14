private void checkEmojiOnly(int[] emojiOnly){
  if (emojiOnly != null && emojiOnly[0] >= 1 && emojiOnly[0] <= 3) {
    TextPaint emojiPaint;
    int size;
switch (emojiOnly[0]) {
case 1:
      emojiPaint=Theme.chat_msgTextPaintOneEmoji;
    size=AndroidUtilities.dp(32);
  emojiOnlyCount=1;
break;
case 2:
emojiPaint=Theme.chat_msgTextPaintTwoEmoji;
size=AndroidUtilities.dp(28);
emojiOnlyCount=2;
break;
case 3:
default :
emojiPaint=Theme.chat_msgTextPaintThreeEmoji;
size=AndroidUtilities.dp(24);
emojiOnlyCount=3;
break;
}
Emoji.EmojiSpan[] spans=((Spannable)messageText).getSpans(0,messageText.length(),Emoji.EmojiSpan.class);
if (spans != null && spans.length > 0) {
for (int a=0; a < spans.length; a++) {
spans[a].replaceFontMetrics(emojiPaint.getFontMetricsInt(),size);
}
}
}
}
