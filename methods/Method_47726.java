@NonNull public static DateUtils.TruncateField getTruncateField(int bucketSize){
  if (bucketSize == 7)   return DateUtils.TruncateField.WEEK_NUMBER;
  if (bucketSize == 31)   return DateUtils.TruncateField.MONTH;
  if (bucketSize == 92)   return DateUtils.TruncateField.QUARTER;
  if (bucketSize == 365)   return DateUtils.TruncateField.YEAR;
  Log.e("ScoreCard",String.format("Unknown bucket size: %d",bucketSize));
  return DateUtils.TruncateField.MONTH;
}
