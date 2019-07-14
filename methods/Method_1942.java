public static void init(final Resources resources){
  if (sPlaceholderDrawable == null) {
    sPlaceholderDrawable=resources.getDrawable(R.color.placeholder);
  }
  if (sErrorDrawable == null) {
    sErrorDrawable=resources.getDrawable(R.color.error);
  }
}
