public static ST_Agsym_s agnewsym(ST_Agraph_s g,CString name,CString value,int id,int kind){
  ENTERING("dbhw2q2jfsz9qwawchy0hxj4i","agnewsym");
  try {
    ST_Agsym_s sym;
    sym=(ST_Agsym_s)agalloc(g,sizeof(ST_Agsym_s.class));
    sym.setInt("kind",kind);
    sym.setPtr("name",agstrdup(g,name));
    sym.setPtr("defval",agstrdup(g,value));
    sym.setInt("id",id);
    return sym;
  }
  finally {
    LEAVING("dbhw2q2jfsz9qwawchy0hxj4i","agnewsym");
  }
}
