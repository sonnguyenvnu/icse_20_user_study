@OnClick({R.id.payV2,R.id.authV2,R.id.h5pay}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.payV2:
    AliPayTool.aliPay(mContext,SelfInfo.ALIPAY_APPID,true,SelfInfo.ALIPAY_RSA2_PRIVATE,new AliPayModel(RxTimeTool.date2String(RxTimeTool.getCurTimeDate()),"0.01","??","????"),new OnSuccessAndErrorListener(){
      @Override public void onSuccess(      String s){
        RxToast.success("????");
      }
      @Override public void onError(      String s){
        RxToast.error("????" + s);
      }
    }
);
  break;
case R.id.authV2:
break;
case R.id.h5pay:
break;
default :
break;
}
}
