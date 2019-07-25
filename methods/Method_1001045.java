public static int topdictsize(ST_Agobj_s obj){
  ENTERING("6az8xu0sgu1d6abu0xfpd89hi","topdictsize");
  try {
    ST_dt_s d;
    d=agdictof(agroot(agraphof(obj)),AGTYPE(obj));
    return d != null ? dtsize_(d) : 0;
  }
  finally {
    LEAVING("6az8xu0sgu1d6abu0xfpd89hi","topdictsize");
  }
}
