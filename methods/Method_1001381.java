public static ST_XLabels_t xlnew(ST_object_t.Array objs,int n_objs,ST_xlabel_t.Array lbls,int n_lbls,ST_label_params_t params){
  ENTERING("88mbfm305igsr7cew5qx6yldp","xlnew");
  try {
    ST_XLabels_t xlp;
    xlp=new ST_XLabels_t();
    xlp.setPtr("hdx",dtopen(Z.z().Hdisc,Z.z().Dtobag));
    if (N(xlp.hdx)) {
      UNSUPPORTED("4t1y5iinm4310lkpvbal1spve");
      UNSUPPORTED("3m406diamp5s5kwcqtwo4pshf");
    }
    xlp.setPtr("spdx",RTreeOpen());
    if (N(xlp.spdx)) {
      UNSUPPORTED("4t1y5iinm4310lkpvbal1spve");
      UNSUPPORTED("3m406diamp5s5kwcqtwo4pshf");
    }
    xlp.objs=objs;
    xlp.setInt("n_objs",n_objs);
    xlp.lbls=lbls;
    xlp.setInt("n_lbls",n_lbls);
    xlp.setPtr("params",params);
    return (ST_XLabels_t)xlp;
  }
  finally {
    LEAVING("88mbfm305igsr7cew5qx6yldp","xlnew");
  }
}
