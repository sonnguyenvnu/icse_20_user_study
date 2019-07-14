private void initDialogResult(Result result){
  BarcodeFormat type=result.getBarcodeFormat();
  String realContent=result.getText();
  if (rxDialogSure == null) {
    rxDialogSure=new RxDialogSure(mContext);
  }
  if (BarcodeFormat.QR_CODE.equals(type)) {
    rxDialogSure.setTitle("???????");
  }
 else   if (BarcodeFormat.EAN_13.equals(type)) {
    rxDialogSure.setTitle("???????");
  }
 else {
    rxDialogSure.setTitle("????");
  }
  rxDialogSure.setContent(realContent);
  rxDialogSure.setSureListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      rxDialogSure.cancel();
    }
  }
);
  rxDialogSure.setOnCancelListener(new DialogInterface.OnCancelListener(){
    @Override public void onCancel(    DialogInterface dialog){
      if (handler != null) {
        handler.sendEmptyMessage(R.id.restart_preview);
      }
    }
  }
);
  if (!rxDialogSure.isShowing()) {
    rxDialogSure.show();
  }
  RxSPTool.putContent(mContext,RxConstants.SP_SCAN_CODE,RxDataTool.stringToInt(RxSPTool.getContent(mContext,RxConstants.SP_SCAN_CODE)) + 1 + "");
}
