private void initDateFormats(){
  if (isInEditMode()) {
    dfMonth=new SimpleDateFormat("MMM",Locale.getDefault());
    dfYear=new SimpleDateFormat("yyyy",Locale.getDefault());
    dfDay=new SimpleDateFormat("d",Locale.getDefault());
  }
 else {
    dfMonth=AndroidDateFormats.fromSkeleton("MMM");
    dfYear=AndroidDateFormats.fromSkeleton("yyyy");
    dfDay=AndroidDateFormats.fromSkeleton("d");
  }
}
