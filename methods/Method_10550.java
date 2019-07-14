@OnClick({R.id.button_error_toast,R.id.button_success_toast,R.id.button_info_toast,R.id.button_warning_toast,R.id.button_normal_toast_wo_icon,R.id.button_normal_toast_w_icon}) public void onClick(View view){
switch (view.getId()) {
case R.id.button_error_toast:
    RxToast.error(mContext,"?????????Toast?",Toast.LENGTH_SHORT,true).show();
  break;
case R.id.button_success_toast:
RxToast.success(mContext,"?????????Toast!",Toast.LENGTH_SHORT,true).show();
break;
case R.id.button_info_toast:
RxToast.info(mContext,"?????????Toast.",Toast.LENGTH_SHORT,true).show();
break;
case R.id.button_warning_toast:
RxToast.warning(mContext,"?????????Toast.",Toast.LENGTH_SHORT,true).show();
break;
case R.id.button_normal_toast_wo_icon:
RxToast.normal(mContext,"?????????ICON?Toast").show();
break;
case R.id.button_normal_toast_w_icon:
Drawable icon=getResources().getDrawable(R.drawable.set);
RxToast.normal(mContext,"?????????ICON?Toast",icon).show();
break;
}
}
