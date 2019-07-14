private void initDateFormats(){
  if (isInEditMode()) {
    dfMonth=new SimpleDateFormat("MMM",Locale.getDefault());
    dfYear=new SimpleDateFormat("yyyy",Locale.getDefault());
  }
 else {
    dfMonth=AndroidDateFormats.fromSkeleton("MMM");
    dfYear=AndroidDateFormats.fromSkeleton("yyyy");
  }
}
