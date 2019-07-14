@OnEvent(ClickEvent.class) static void onClickAbsoluteChild(ComponentContext c,@Param String absoluteParam){
  Toast.makeText(c.getAndroidContext(),"Absolute child clicked: " + absoluteParam,LENGTH_SHORT).show();
}
