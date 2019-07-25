/** 
 * Installs the given EmojiProvider. NOTE: That only one can be present at any time.
 * @param provider the provider that should be installed.
 */
public static void install(@NonNull final EmojiProvider provider){
synchronized (EmojiManager.class) {
    INSTANCE.categories=checkNotNull(provider.getCategories(),"categories == null");
    INSTANCE.emojiMap.clear();
    INSTANCE.emojiReplacer=provider instanceof EmojiReplacer ? (EmojiReplacer)provider : DEFAULT_EMOJI_REPLACER;
    final List<String> unicodesForPattern=new ArrayList<>(GUESSED_UNICODE_AMOUNT);
    final int categoriesSize=INSTANCE.categories.length;
    for (int i=0; i < categoriesSize; i++) {
      final Emoji[] emojis=checkNotNull(INSTANCE.categories[i].getEmojis(),"emojies == null");
      final int emojisSize=emojis.length;
      for (int j=0; j < emojisSize; j++) {
        final Emoji emoji=emojis[j];
        final String unicode=emoji.getUnicode();
        final List<Emoji> variants=emoji.getVariants();
        INSTANCE.emojiMap.put(unicode,emoji);
        unicodesForPattern.add(unicode);
        for (int k=0; k < variants.size(); k++) {
          final Emoji variant=variants.get(k);
          final String variantUnicode=variant.getUnicode();
          INSTANCE.emojiMap.put(variantUnicode,variant);
          unicodesForPattern.add(variantUnicode);
        }
      }
    }
    if (unicodesForPattern.isEmpty()) {
      throw new IllegalArgumentException("Your EmojiProvider must at least have one category with at least one emoji.");
    }
    Collections.sort(unicodesForPattern,STRING_LENGTH_COMPARATOR);
    final StringBuilder patternBuilder=new StringBuilder(GUESSED_TOTAL_PATTERN_LENGTH);
    final int unicodesForPatternSize=unicodesForPattern.size();
    for (int i=0; i < unicodesForPatternSize; i++) {
      patternBuilder.append(Pattern.quote(unicodesForPattern.get(i))).append('|');
    }
    final String regex=patternBuilder.deleteCharAt(patternBuilder.length() - 1).toString();
    INSTANCE.emojiPattern=Pattern.compile(regex);
    INSTANCE.emojiRepetitivePattern=Pattern.compile('(' + regex + ")+");
  }
}
