public static void serialize(BrokerRegisterRequest request,ByteBuf out){
  out.writeInt(request.getRequestType());
  PayloadHolderUtils.writeString(request.getGroupName(),out);
  PayloadHolderUtils.writeString(request.getBrokerAddress(),out);
  out.writeInt(request.getBrokerRole());
  out.writeInt(request.getBrokerState());
}
