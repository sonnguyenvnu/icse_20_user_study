public CQL3Type.Raw collection(CQL3Type.Raw rawType){
switch (cqlCollection()) {
case LIST:
    return CQL3Type.Raw.list(rawType);
case SET:
  return CQL3Type.Raw.set(rawType);
default :
return rawType;
}
}
