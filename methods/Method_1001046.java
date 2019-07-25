public static ST_Agrec_s agmakeattrs(ST_Agraph_s context,__ptr__ obj){
  ENTERING("3wjrlyjdlz8k9nfxenxsfiqmj","agmakeattrs");
  try {
    int sz;
    ST_Agattr_s rec;
    ST_Agsym_s sym;
    ST_dt_s datadict;
    rec=(ST_Agattr_s)agbindrec(obj,AgDataRecName,sizeof(ST_Agattr_s.class),false).castTo(ST_Agattr_s.class);
    datadict=agdictof(context,AGTYPE(obj));
    if (rec.dict == null) {
      rec.dict=agdictof(agroot(context),AGTYPE(obj));
      sz=topdictsize((ST_Agobj_s)obj.castTo(ST_Agobj_s.class));
      if (sz < 4)       sz=4;
      rec.str=new ArrayList<CString>();
      for (int i=0; i < sz; i++)       rec.str.add(null);
      for (sym=(ST_Agsym_s)((__ptr__)datadict.searchf.exe(datadict,null,0000200)); sym != null; sym=(ST_Agsym_s)((__ptr__)datadict.searchf.exe(datadict,sym,0000010)))       rec.str.set(sym.id,agstrdup(agraphof(obj),sym.defval));
    }
 else {
    }
    return (ST_Agrec_s)rec.castTo(ST_Agrec_s.class);
  }
  finally {
    LEAVING("3wjrlyjdlz8k9nfxenxsfiqmj","agmakeattrs");
  }
}
