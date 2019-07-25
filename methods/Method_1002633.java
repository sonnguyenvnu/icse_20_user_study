@Benchmark public void all(Blackhole bh){
  bh.consume(MEDIA_TYPES.match(GRPC_MEDIA_TYPE));
  bh.consume(MEDIA_TYPES.match(NOT_GRPC_MEDIA_TYPE));
  bh.consume(MEDIA_TYPES.match(GRPC_MEDIA_TYPE_WITH_PARAMS));
  bh.consume(MEDIA_TYPES.match(NOT_GRPC_MEDIA_TYPE_WITH_PARAMS));
}
