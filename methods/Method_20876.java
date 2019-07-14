/** 
 * Returns a SpannableString that shrinks and centers currency code if it's necessary. Special case: US people looking at US currency just get the currency symbol.
 */
public static @NotNull SpannableString styleCurrency(final double value,final Project project,final @NonNull KSCurrency ksCurrency){
  return styleCurrency(value,project,ksCurrency,true);
}
