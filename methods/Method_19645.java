@OnEvent(ClickEvent.class) static void onClickSecondChild(ComponentContext c,@Prop String secondChildString){
  Toast.makeText(c.getAndroidContext(),"Second child clicked: " + secondChildString,LENGTH_SHORT).show();
}
