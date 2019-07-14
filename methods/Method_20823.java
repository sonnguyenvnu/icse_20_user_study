/** 
 * Returns a formatted number for a given locale.  {@link NumberOptions} can control whether the number isused as a currency, if it is bucketed, and the precision.
 */
public static @NonNull String format(final float value,final @NonNull NumberOptions options,final @NonNull Locale locale){
  final NumberFormat numberFormat=numberFormat(options,locale);
  if (numberFormat instanceof DecimalFormat) {
    numberFormat.setRoundingMode(ObjectUtils.coalesce(options.roundingMode(),RoundingMode.HALF_DOWN));
  }
  int precision=ObjectUtils.coalesce(options.precision(),0);
  float divisor=1.0f;
  String suffix="";
  final float bucketAbove=ObjectUtils.coalesce(options.bucketAbove(),0.0f);
  if (bucketAbove >= 1000.0f && value >= bucketAbove) {
    if (bucketAbove > 0.0f && bucketAbove < 1_000_000.0f) {
      divisor=1000.0f;
      suffix="K";
    }
 else     if (bucketAbove >= 1_000_000.0f) {
      divisor=1_000_000.0f;
      suffix="M";
    }
    if (options.bucketAbove() != null) {
      precision=ObjectUtils.coalesce(options.bucketPrecision(),0);
    }
  }
  if (options.currencyCode() != null) {
    suffix=String.format("%s %s",suffix,options.currencyCode());
  }
  numberFormat.setMinimumFractionDigits(precision);
  numberFormat.setMaximumFractionDigits(precision);
  float bucketedValue=value;
  if (value >= bucketAbove) {
    bucketedValue=value / divisor;
  }
  return String.format("%s%s",numberFormat.format(bucketedValue),suffix).trim();
}
