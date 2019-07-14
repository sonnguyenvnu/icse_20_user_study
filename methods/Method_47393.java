public int convertPxToDb(float px){
  return Math.round(px / activity.getResources().getDisplayMetrics().density);
}
