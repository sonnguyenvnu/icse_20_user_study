private void initView(){
  getTitleView().setBackgroundDrawable(null);
  setTitle("GPS???");
  getTitleView().setTextSize(16f);
  getTitleView().setTextColor(Color.BLACK);
  setContent("???????????GPS??????");
  getSureView().setText("???");
  getCancelView().setText("???");
  getSureView().setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      RxLocationTool.openGpsSettings(mContext);
      cancel();
    }
  }
);
  getCancelView().setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      cancel();
    }
  }
);
  setCanceledOnTouchOutside(false);
  setCancelable(false);
}
