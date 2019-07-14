public void showAspectRatioDialog(){
  if (areaView.getLockAspectRatio() > 0) {
    areaView.setLockedAspectRatio(0);
    if (listener != null) {
      listener.onAspectLock(false);
    }
    return;
  }
  if (hasAspectRatioDialog) {
    return;
  }
  hasAspectRatioDialog=true;
  String[] actions=new String[8];
  final Integer[][] ratios=new Integer[][]{new Integer[]{3,2},new Integer[]{5,3},new Integer[]{4,3},new Integer[]{5,4},new Integer[]{7,5},new Integer[]{16,9}};
  actions[0]=LocaleController.getString("CropOriginal",R.string.CropOriginal);
  actions[1]=LocaleController.getString("CropSquare",R.string.CropSquare);
  int i=2;
  for (  Integer[] ratioPair : ratios) {
    if (areaView.getAspectRatio() > 1.0f) {
      actions[i]=String.format("%d:%d",ratioPair[0],ratioPair[1]);
    }
 else {
      actions[i]=String.format("%d:%d",ratioPair[1],ratioPair[0]);
    }
    i++;
  }
  AlertDialog dialog=new AlertDialog.Builder(getContext()).setItems(actions,(dialog12,which) -> {
    hasAspectRatioDialog=false;
switch (which) {
case 0:
{
        float w=state.getBaseRotation() % 180 != 0 ? state.getHeight() : state.getWidth();
        float h=state.getBaseRotation() % 180 != 0 ? state.getWidth() : state.getHeight();
        setLockedAspectRatio(w / h);
      }
    break;
case 1:
{
    setLockedAspectRatio(1.0f);
  }
break;
default :
{
Integer[] ratioPair=ratios[which - 2];
if (areaView.getAspectRatio() > 1.0f) {
  setLockedAspectRatio(ratioPair[0] / (float)ratioPair[1]);
}
 else {
  setLockedAspectRatio(ratioPair[1] / (float)ratioPair[0]);
}
}
break;
}
}
).create();
dialog.setCanceledOnTouchOutside(true);
dialog.setOnCancelListener(dialog1 -> hasAspectRatioDialog=false);
dialog.show();
}
