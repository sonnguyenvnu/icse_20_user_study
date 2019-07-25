public static StarStruct create(Class theClass,StarStruct parent){
  if (theClass == ST_dtmethod_s.class) {
    throw new IllegalArgumentException(theClass.toString());
  }
  if (theClass == ST_dtdisc_s.class) {
    return new ST_dtdisc_s(parent);
  }
  if (theClass == ST_Agdesc_s.class) {
    return new ST_Agdesc_s(parent);
  }
  if (theClass == ST_nlist_t.class) {
    return new ST_nlist_t(parent);
  }
  if (theClass == ST_elist.class) {
    return new ST_elist(parent);
  }
  if (theClass == ST_pointf.class) {
    return new ST_pointf(parent);
  }
  if (theClass == ST_boxf.class) {
    return new ST_boxf(parent);
  }
  if (theClass == ST_port.class) {
    return new ST_port(parent);
  }
  if (theClass == ST_polygon_t.class) {
    return new ST_polygon_t(parent);
  }
  if (theClass == ST_shape_functions.class) {
    return new ST_shape_functions(parent);
  }
  if (theClass == ST_shape_desc.class) {
    return new ST_shape_desc(parent);
  }
  if (theClass == ST_Ppoly_t.class) {
    return new ST_Ppoly_t(parent);
  }
  if (theClass == ST_splineInfo.class) {
    return new ST_splineInfo(parent);
  }
  if (theClass == ST_textfont_t.class) {
    return new ST_textfont_t(parent);
  }
  if (theClass == ST_Agsubnode_s.class) {
    return new ST_Agsubnode_s(parent);
  }
  if (theClass == ST_dtlink_s.class) {
    return new ST_dtlink_s(parent);
  }
  if (theClass == ST_refstr_t.class) {
    return new ST_refstr_t();
  }
  if (theClass == ST_refstr_t.class) {
    return new ST_refstr_t();
  }
  if (theClass == ST_Agsym_s.class) {
    return new ST_Agsym_s(parent);
  }
  if (theClass == ST_Agedge_s.class) {
    return new ST_Agedge_s(parent);
  }
  if (theClass == ST_Agobj_s.class) {
    return new ST_Agobj_s(parent);
  }
  if (theClass == ST_Agrec_s.class) {
    return new ST_Agrec_s(parent);
  }
  if (theClass == ST_Agraph_s.class) {
    return new ST_Agraph_s(parent);
  }
  if (theClass == ST_Agclos_s.class) {
    return new ST_Agclos_s(parent);
  }
  if (theClass == ST_Agdisc_s.class) {
    return new ST_Agdisc_s(parent);
  }
  if (theClass == Agdstate_s.class) {
    return new ST_Agdstate_s(parent);
  }
  if (theClass == ST_dt_s.class) {
    return new ST_dt_s(parent);
  }
  if (theClass == ST_dtdata_s.class) {
    return new ST_dtdata_s(parent);
  }
  if (theClass == ST_Agdatadict_s.class) {
    return new ST_Agdatadict_s(parent);
  }
  if (theClass == ST_Agattr_s.class) {
    return new ST_Agattr_s(parent);
  }
  if (theClass == Agcbstack_s.class) {
    return new ST_Agcbstack_s(parent);
  }
  if (theClass == ST_Agnode_s.class) {
    return new ST_Agnode_s(parent);
  }
  if (theClass == ST_Agedgepair_s.class) {
    return new ST_Agedgepair_s(parent);
  }
  if (theClass == ST_Agraphinfo_t.class) {
    return new ST_Agraphinfo_t(parent);
  }
  if (theClass == ST_GVC_s.class) {
    return new ST_GVC_s(parent);
  }
  if (theClass == GVCOMMON_t.class) {
    return new ST_GVCOMMON_t(parent);
  }
  if (theClass == layout_t.class) {
    return new ST_layout_t(parent);
  }
  if (theClass == ST_Agnodeinfo_t.class) {
    return new ST_Agnodeinfo_t(parent);
  }
  if (theClass == ST_textlabel_t.class) {
    return new ST_textlabel_t(parent);
  }
  if (theClass == ST_textspan_t.class) {
    return new ST_textspan_t(parent);
  }
  if (theClass == rank_t.class) {
    return new ST_rank_t(parent);
  }
  if (theClass == ST_Agedgeinfo_t.class) {
    return new ST_Agedgeinfo_t(parent);
  }
  if (theClass == ST_splines.class) {
    return new ST_splines(parent);
  }
  if (theClass == ST_bezier.class) {
    return new ST_bezier(parent);
  }
  if (theClass == ST_dthold_s.class) {
    return new ST_dthold_s(parent);
  }
  if (theClass == ST_pack_info.class) {
    return new ST_pack_info(parent);
  }
  if (theClass == ST_aspect_t.class) {
    return new ST_aspect_t(parent);
  }
  if (theClass == ST_fontinfo.class) {
    return new ST_fontinfo(parent);
  }
  if (theClass == ST_IMapEntry_t.class) {
    return new ST_IMapEntry_t(parent);
  }
  if (theClass == ST_nodequeue.class) {
    return new ST_nodequeue(parent);
  }
  if (theClass == ST_spline_info_t.class) {
    return new ST_spline_info_t(parent);
  }
  if (theClass == ST_path.class) {
    return new ST_path(parent);
  }
  if (theClass == ST_pathend_t.class) {
    return new ST_pathend_t(parent);
  }
  if (theClass == ST_triangle_t.class) {
    return new ST_triangle_t(parent);
  }
  if (theClass == tedge_t.class) {
    return new ST_tedge_t(parent);
  }
  if (theClass == ST_Pedge_t.class) {
    return new ST_Pedge_t(parent);
  }
  if (theClass == ST_tna_t.class) {
    return new ST_tna_t(parent);
  }
  if (theClass == ST_label_params_t.class) {
    return new ST_label_params_t(parent);
  }
  if (theClass == ST_object_t.class) {
    return new ST_object_t(parent);
  }
  if (theClass == ST_xlabel_t.class) {
    return new ST_xlabel_t(parent);
  }
  if (theClass == ST_XLabels_t.class) {
    return new ST_XLabels_t(parent);
  }
  if (theClass == ST_HDict_t.class) {
    return new ST_HDict_t(parent);
  }
  if (theClass == ST_RTree.class) {
    return new ST_RTree(parent);
  }
  if (theClass == ST_Node_t___.class) {
    return new ST_Node_t___(parent);
  }
  if (theClass == ST_cinfo_t.class) {
    return new ST_cinfo_t(parent);
  }
  notFound(theClass);
  throw new UnsupportedOperationException();
}
