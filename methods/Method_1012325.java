private void process(){
  emojiCharSequence=EmojiCompat.get().process(emojiCharSequence);
  if (emojiCharSequence instanceof Spanned) {
    final Object[] spans=((Spanned)emojiCharSequence).getSpans(0,emojiCharSequence.length(),EmojiSpan.class);
    if (spans.length > 0) {
      emojiSpan=(EmojiSpan)spans[0];
    }
  }
}
