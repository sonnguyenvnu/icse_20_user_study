public static boolean treesearch(ST_Agnode_s v){
  ENTERING("1gvyafmercq92v3lg6gb33cbt","treesearch");
  try {
    int i;
    ST_Agedge_s e;
    for (i=0; (e=(ST_Agedge_s)ND_out(v).getFromList(i)) != null; i++) {
      if ((ND_mark(aghead(e)) == 0) && (SLACK(e) == 0)) {
        add_tree_edge(e);
        if ((Z.z().Tree_edge.size == Z.z().N_nodes - 1) || treesearch(aghead(e)))         return NOT(0);
      }
    }
    for (i=0; (e=(ST_Agedge_s)ND_in(v).getFromList(i)) != null; i++) {
      if ((ND_mark(agtail(e)) == 0) && (SLACK(e) == 0)) {
        add_tree_edge(e);
        if ((Z.z().Tree_edge.size == Z.z().N_nodes - 1) || treesearch(agtail(e)))         return NOT(0);
      }
    }
    return false;
  }
  finally {
    LEAVING("1gvyafmercq92v3lg6gb33cbt","treesearch");
  }
}
