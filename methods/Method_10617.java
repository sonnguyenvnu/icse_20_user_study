public static void wechatPayApp(Context mContext,String appid,String mch_id,String wx_private_key,SortedMap<String,String> params,OnSuccessAndErrorListener onRxHttp){
  String sign=getSign(params,wx_private_key);
  WechatPayModel wechatPayModel=new WechatPayModel(appid,mch_id,params.get("prepayid"),"Sign=WechatPay",params.get("noncestr"),params.get("timestamp"),sign);
  String pay_param=new Gson().toJson(wechatPayModel);
  WechatPayTools.doWXPay(mContext,appid,pay_param,onRxHttp);
}
