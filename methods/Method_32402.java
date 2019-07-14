/** 
 * Returns a word based formatter for the JDK default locale. <p> This calls  {@link #wordBased(Locale)} using the {@link Locale#getDefault() default locale}.
 * @return the formatter, not null
 * @since 2.0
 */
public static PeriodFormatter wordBased(){
  return wordBased(Locale.getDefault());
}
