private static Object readAmfData(ParsableByteArray data,int type){
switch (type) {
case AMF_TYPE_NUMBER:
    return readAmfDouble(data);
case AMF_TYPE_BOOLEAN:
  return readAmfBoolean(data);
case AMF_TYPE_STRING:
return readAmfString(data);
case AMF_TYPE_OBJECT:
return readAmfObject(data);
case AMF_TYPE_ECMA_ARRAY:
return readAmfEcmaArray(data);
case AMF_TYPE_STRICT_ARRAY:
return readAmfStrictArray(data);
case AMF_TYPE_DATE:
return readAmfDate(data);
default :
return null;
}
}
