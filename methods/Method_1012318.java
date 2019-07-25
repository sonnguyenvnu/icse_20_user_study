public EmojiGridView init(@Nullable final OnEmojiClickListener onEmojiClickListener,@Nullable final OnEmojiLongClickListener onEmojiLongClickListener,@NonNull final EmojiCategory category,@NonNull final VariantEmoji variantManager){
  emojiArrayAdapter=new EmojiArrayAdapter(getContext(),category.getEmojis(),variantManager,onEmojiClickListener,onEmojiLongClickListener);
  setAdapter(emojiArrayAdapter);
  return this;
}
