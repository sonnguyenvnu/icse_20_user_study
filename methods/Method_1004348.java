private RecordQuery deserialize(HttpServletRequest req){
  try {
    final String recordQueryStr=req.getParameter("recordQuery");
    return serializer.deSerialize(recordQueryStr,RecordQuery.class);
  }
 catch (  Exception e) {
    LOG.error("Get record query failed.",e);
    return null;
  }
}
