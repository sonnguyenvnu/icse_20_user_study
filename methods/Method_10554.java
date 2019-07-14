@OnClick({R.id.btn_vibrate_once,R.id.btn_vibrate_Complicated,R.id.btn_vibrate_stop}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.btn_vibrate_once:
    RxVibrateTool.vibrateOnce(this,2000);
  break;
case R.id.btn_vibrate_Complicated:
RxVibrateTool.vibrateComplicated(this,temp,0);
break;
case R.id.btn_vibrate_stop:
RxVibrateTool.vibrateStop();
break;
}
}
