public int convertDbToPx(float dp){
  return Math.round(activity.getResources().getDisplayMetrics().density * dp);
}
