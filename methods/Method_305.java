@OnClick(R2.id.hello) void sayHello(){
  Toast.makeText(this,"Hello, views!",LENGTH_SHORT).show();
  ButterKnife.apply(headerViews,ALPHA_FADE);
}
