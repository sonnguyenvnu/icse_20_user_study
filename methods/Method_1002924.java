/** 
 * Executes a unary gRPC client request. The given  {@code payload} will be framed and sent to the path at{@code uri}.  {@code uri} should be the method's URI, which is always of the format{@code /:package-name.:service-name/:method}. For example, for the proto package {@code armeria.protocol}, the service name  {@code CoolService} and the method name{@code RunWithoutStubs}, the  {@code uri} would be {@code /armeria.protocol.CoolService/RunWithoutStubs}. If you aren't sure what the package, service name, and method name are for your method, you should probably use normal gRPC stubs instead of this class.
 */
public CompletableFuture<byte[]> execute(String uri,byte[] payload){
  final HttpRequest request=HttpRequest.of(RequestHeaders.of(HttpMethod.POST,uri,HttpHeaderNames.CONTENT_TYPE,"application/grpc+proto"),HttpData.wrap(payload));
  return httpClient.execute(request).aggregate().thenApply(msg -> {
    if (!HttpStatus.OK.equals(msg.status())) {
      throw new ArmeriaStatusException(StatusCodes.INTERNAL,"Non-successful HTTP response code: " + msg.status());
    }
    final String grpcStatus=msg.headers().get(GrpcHeaderNames.GRPC_STATUS);
    if (grpcStatus != null && !"0".equals(grpcStatus)) {
      String grpcMessage=msg.headers().get(GrpcHeaderNames.GRPC_MESSAGE);
      if (grpcMessage != null) {
        grpcMessage=StatusMessageEscaper.unescape(grpcMessage);
      }
      throw new ArmeriaStatusException(Integer.parseInt(grpcStatus),grpcMessage);
    }
    return msg.content().array();
  }
);
}
