/** 
 * Gets the default formatter that outputs words in English. <p> This calls  {@link #wordBased(Locale)} using a locale of {@code ENGLISH}.
 * @return the formatter, not null
 */
public static PeriodFormatter getDefault(){
  return wordBased(Locale.ENGLISH);
}
