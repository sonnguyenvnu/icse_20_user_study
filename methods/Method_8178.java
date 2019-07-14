private void fillNextCodeParams(Bundle params,TLRPC.TL_auth_sentCode res){
  params.putString("phoneHash",res.phone_code_hash);
  if (res.next_type instanceof TLRPC.TL_auth_codeTypeCall) {
    params.putInt("nextType",4);
  }
 else   if (res.next_type instanceof TLRPC.TL_auth_codeTypeFlashCall) {
    params.putInt("nextType",3);
  }
 else   if (res.next_type instanceof TLRPC.TL_auth_codeTypeSms) {
    params.putInt("nextType",2);
  }
  if (res.type instanceof TLRPC.TL_auth_sentCodeTypeApp) {
    params.putInt("type",1);
    params.putInt("length",res.type.length);
    setPage(1,true,params,false);
  }
 else {
    if (res.timeout == 0) {
      res.timeout=60;
    }
    params.putInt("timeout",res.timeout * 1000);
    if (res.type instanceof TLRPC.TL_auth_sentCodeTypeCall) {
      params.putInt("type",4);
      params.putInt("length",res.type.length);
      setPage(4,true,params,false);
    }
 else     if (res.type instanceof TLRPC.TL_auth_sentCodeTypeFlashCall) {
      params.putInt("type",3);
      params.putString("pattern",res.type.pattern);
      setPage(3,true,params,false);
    }
 else     if (res.type instanceof TLRPC.TL_auth_sentCodeTypeSms) {
      params.putInt("type",2);
      params.putInt("length",res.type.length);
      setPage(2,true,params,false);
    }
  }
}
