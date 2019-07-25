public static void installnode(ST_Agraph_s g,ST_Agnode_s n){
  ENTERING("4m26dpgaiw44hcleugjy71eus","installnode");
  try {
    ST_Agsubnode_s sn;
    int osize;
    osize=dtsize_((ST_dt_s)g.n_id);
    if (EQ(g,agroot(g)))     sn=(ST_Agsubnode_s)n.mainsub;
 else     sn=(ST_Agsubnode_s)((__ptr__)agalloc(g,sizeof(ST_Agsubnode_s.class))).castTo(ST_Agsubnode_s.class);
    sn.setPtr("node",n);
    g.n_id.searchf.exe(g.n_id,sn,0000001);
    g.n_seq.searchf.exe(g.n_seq,sn,0000001);
  }
  finally {
    LEAVING("4m26dpgaiw44hcleugjy71eus","installnode");
  }
}
