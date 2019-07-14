public int getTextGravity(){
  int gravity;
switch (mTextGravity) {
case GRAVITY_CENTER:
    gravity=android.view.Gravity.CENTER;
  break;
case GRAVITY_LEFT:
gravity=android.view.Gravity.START;
break;
case GRAVITY_RIGHT:
gravity=android.view.Gravity.END;
break;
default :
gravity=android.view.Gravity.CENTER;
}
return gravity;
}
