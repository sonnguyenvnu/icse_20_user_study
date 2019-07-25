public static CString agxget(__ptr__ obj,ST_Agsym_s sym){
  ENTERING("9h5oymhfkp6k34zl0fonn10k9","agxget");
  try {
    ST_Agattr_s data;
    CString rv;
    data=agattrrec(obj.castTo(ST_Agobj_s.class));
    rv=data.str.get(sym.id);
    return rv;
  }
  finally {
    LEAVING("9h5oymhfkp6k34zl0fonn10k9","agxget");
  }
}
