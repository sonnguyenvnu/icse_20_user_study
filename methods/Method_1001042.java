public static ST_Agdatadict_s agdatadict(ST_Agraph_s g,boolean cflag){
  ENTERING("4bm10isw1qq1eqcse8afbxee3","agdatadict");
  try {
    ST_Agdatadict_s rv;
    rv=(ST_Agdatadict_s)aggetrec(g,DataDictName,false).castTo(ST_Agdatadict_s.class);
    if (rv != null || N(cflag))     return rv;
    init_all_attrs(g);
    rv=(ST_Agdatadict_s)aggetrec(g,DataDictName,false).castTo(ST_Agdatadict_s.class);
    return rv;
  }
  finally {
    LEAVING("4bm10isw1qq1eqcse8afbxee3","agdatadict");
  }
}
