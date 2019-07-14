public void setLeftFinish(final Activity activity){
  mLlLeft.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      activity.finish();
    }
  }
);
}
