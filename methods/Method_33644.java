@Override protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_nav_about);
  showContentView();
  setTitle("????");
  bindingView.tvVersionName.setText("???? V" + BaseTools.getVersionName());
  Glide.with(this).load(R.drawable.ic_cloudreader).into(bindingView.ivIcon);
  bindingView.tvGankio.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
  bindingView.tvGankio.getPaint().setAntiAlias(true);
  bindingView.tvDouban.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
  bindingView.tvDouban.getPaint().setAntiAlias(true);
  bindingView.tvWanandroid.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
  bindingView.tvWanandroid.getPaint().setAntiAlias(true);
  initListener();
}
