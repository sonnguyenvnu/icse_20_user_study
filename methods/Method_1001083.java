public static CString agnameof(__ptr__ obj){
  ENTERING("cctsybrl54fy799aynfej4iiy","agnameof");
  try {
    ST_Agraph_s g;
    CString rv;
    g=agraphof(obj);
    if ((rv=aginternalmapprint(g,AGTYPE(obj),AGID(obj))) != null)     return rv;
    if (g.clos.disc.id.print != null) {
      if ((rv=(CString)g.clos.disc.id.print.exe(g.clos.state.id,AGTYPE(obj),AGID(obj))) != null)       return rv;
    }
    if (AGTYPE(obj) != AGEDGE) {
      rv=new CString("%" + ((ST_Agobj_s)obj.castTo(ST_Agobj_s.class)).tag.id);
    }
 else     rv=null;
    return rv;
  }
  finally {
    LEAVING("cctsybrl54fy799aynfej4iiy","agnameof");
  }
}
