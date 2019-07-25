public String format(DecimalFormat format){
  if (nums.size() == 1 && separators.size() == 0) {
    return format.format(nums.get(0));
  }
  return "<b>" + toString() + "</b>";
}
