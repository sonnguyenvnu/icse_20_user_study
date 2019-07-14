protected final ResponseSetting asResponseSetting(){
  ResponseSetting responseSetting=asBaseResourceSetting(new ResponseSetting());
  responseSetting.status=status;
  responseSetting.proxy=proxy;
  responseSetting.headers=headers;
  responseSetting.cookies=cookies;
  responseSetting.latency=latency;
  responseSetting.version=version;
  responseSetting.attachment=attachment;
  responseSetting.seq=seq;
  responseSetting.cycle=cycle;
  return responseSetting;
}
