@OnClick(R.id.push_notifications_button) public void pushNotificationsButtonClick(){
  final View view=View.inflate(this,R.layout.debug_push_notifications_layout,null);
  new AlertDialog.Builder(this).setTitle("Push notifications").setView(view).show();
}
