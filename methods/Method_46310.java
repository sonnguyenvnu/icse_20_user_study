/** 
 * ??????????????
 * @param serialization ???????
 * @return ?????
 */
public static byte getSerializeTypeByName(String serialization) throws SofaRpcException {
  String sz=serialization.toLowerCase();
  Byte code;
  if (RpcConstants.SERIALIZE_HESSIAN2.equals(sz) || RpcConstants.SERIALIZE_HESSIAN.equals(sz)) {
    code=SerializerFactory.getCodeByAlias(RpcConstants.SERIALIZE_HESSIAN2);
  }
 else {
    code=SerializerFactory.getCodeByAlias(serialization);
  }
  if (code != null) {
    return code;
  }
 else {
    throw new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE,"Unsupported serialize type " + serialization + " in http protocol.");
  }
}
