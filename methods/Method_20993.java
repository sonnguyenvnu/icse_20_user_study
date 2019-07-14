public boolean isFeaturedToday(){
  if (featuredAt() == null) {
    return false;
  }
  return DateTimeUtils.isDateToday(featuredAt());
}
