/** 
 * ????
 * @param joined
 * @param list
 */
private void setPraise(boolean joined,List<User> list){
  ivMomentViewPraise.setImageResource(joined ? R.drawable.praised : R.drawable.praise);
  llMomentViewPraise.setVisibility(list == null || list.isEmpty() ? View.GONE : View.VISIBLE);
  if (llMomentViewPraise.getVisibility() == View.VISIBLE) {
    tvMomentViewPraise.setView(list);
  }
}
