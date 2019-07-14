static String getRatingUnavailableReason(String ratingUnavailableReason,Context context){
  return !TextUtils.isEmpty(ratingUnavailableReason) ? ratingUnavailableReason : context.getString(R.string.item_rating_unavailable_reason_fallback);
}
