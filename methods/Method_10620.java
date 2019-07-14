public static void doWXPay(Context mContext,String wx_appid,String pay_param,final OnSuccessAndErrorListener onRxHttpString){
  WechatPay.init(mContext,wx_appid);
  WechatPay.getInstance().doPay(pay_param,new WechatPay.WXPayResultCallBack(){
    @Override public void onSuccess(){
      RxToast.success("??????");
      onRxHttpString.onSuccess("??????");
    }
    @Override public void onError(    int error_code){
switch (error_code) {
case WechatPay.NO_OR_LOW_WX:
        RxToast.error("????????????");
      onRxHttpString.onError("????????????");
    break;
case WechatPay.ERROR_PAY_PARAM:
  RxToast.error("????");
onRxHttpString.onError("????");
break;
case WechatPay.ERROR_PAY:
RxToast.error("????");
onRxHttpString.onError("????");
break;
}
}
@Override public void onCancel(){
RxToast.error("????");
onRxHttpString.onError("????");
}
}
);
}
