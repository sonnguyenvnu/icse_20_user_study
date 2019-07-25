public static void addattr(ST_Agraph_s g,ST_Agobj_s obj,ST_Agsym_s sym){
  ENTERING("2io7b26wq70e7kwdlzsh6bw7f","addattr");
  try {
    ST_Agattr_s attr;
    attr=(ST_Agattr_s)agattrrec(obj);
    if (sym.id >= 4)     attr.str.add(null);
    attr.str.set(sym.id,agstrdup(g,sym.defval));
  }
  finally {
    LEAVING("2io7b26wq70e7kwdlzsh6bw7f","addattr");
  }
}
