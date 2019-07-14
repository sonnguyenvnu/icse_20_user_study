protected Representation buildRepresentation(RepresentationInfo representationInfo,String extraDrmSchemeType,ArrayList<SchemeData> extraDrmSchemeDatas,ArrayList<Descriptor> extraInbandEventStreams){
  Format format=representationInfo.format;
  String drmSchemeType=representationInfo.drmSchemeType != null ? representationInfo.drmSchemeType : extraDrmSchemeType;
  ArrayList<SchemeData> drmSchemeDatas=representationInfo.drmSchemeDatas;
  drmSchemeDatas.addAll(extraDrmSchemeDatas);
  if (!drmSchemeDatas.isEmpty()) {
    filterRedundantIncompleteSchemeDatas(drmSchemeDatas);
    DrmInitData drmInitData=new DrmInitData(drmSchemeType,drmSchemeDatas);
    format=format.copyWithDrmInitData(drmInitData);
  }
  ArrayList<Descriptor> inbandEventStreams=representationInfo.inbandEventStreams;
  inbandEventStreams.addAll(extraInbandEventStreams);
  return Representation.newInstance(representationInfo.revisionId,format,representationInfo.baseUrl,representationInfo.segmentBase,inbandEventStreams);
}
