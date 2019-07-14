@NonNull protected String deviceFormat(){
  return this.context.getResources().getBoolean(R.bool.isTablet) ? "tablet" : "phone";
}
