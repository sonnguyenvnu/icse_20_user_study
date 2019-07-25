public static CString agget(__ptr__ obj,CString name){
  ENTERING("eydjyhexv5jr6vi7uhk0cgphv","agget");
  try {
    ST_Agsym_s sym;
    ST_Agattr_s data;
    CString rv=null;
    sym=agattrsym(obj,name);
    if (sym == null)     rv=null;
 else {
      data=agattrrec(obj.castTo(ST_Agobj_s.class));
      rv=data.str.get(sym.id);
    }
    return rv;
  }
  finally {
    LEAVING("eydjyhexv5jr6vi7uhk0cgphv","agget");
  }
}
