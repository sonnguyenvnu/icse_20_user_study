public void parse(BoolDecoder bc,boolean segmentation_enabled,boolean mb_segement_abs_delta) throws IOException {
  qIndex=bc.readLiteral(7);
  boolean q_update=false;
  DeltaQ v=get_delta_q(bc,0);
  int y1dc_delta_q=v.v;
  q_update=q_update || v.update;
  v=get_delta_q(bc,0);
  int y2dc_delta_q=v.v;
  q_update=q_update || v.update;
  v=get_delta_q(bc,0);
  int y2ac_delta_q=v.v;
  q_update=q_update || v.update;
  v=get_delta_q(bc,0);
  int uvdc_delta_q=v.v;
  q_update=q_update || v.update;
  v=get_delta_q(bc,0);
  int uvac_delta_q=v.v;
  q_update=q_update || v.update;
  for (  SegmentQuant s : segQuants) {
    if (!segmentation_enabled) {
      s.setQindex(qIndex);
    }
 else     if (!mb_segement_abs_delta) {
      s.setQindex(s.getQindex() + qIndex);
    }
    s.setY1dc(y1dc_delta_q);
    s.setY2dc(y2dc_delta_q);
    s.setY2ac_delta_q(y2ac_delta_q);
    s.setUvdc_delta_q(uvdc_delta_q);
    s.setUvac_delta_q(uvac_delta_q);
  }
}
