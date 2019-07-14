public void init(Activity activity){
  this.activity=activity;
  onButtonClickListener=new OnButtonClickListener();
  setOnClickListener(onButtonClickListener);
}
