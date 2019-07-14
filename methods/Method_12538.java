@SuppressWarnings("unchecked") private static <S,T>Function<Flux<DataBuffer>,Flux<DataBuffer>> convertUsing(ParameterizedTypeReference<S> sourceType,ParameterizedTypeReference<T> targetType,Function<S,T> converterFn){
  return input -> DECODER.decodeToMono(input,ResolvableType.forType(sourceType),null,null).map(body -> converterFn.apply((S)body)).flatMapMany(output -> ENCODER.encode(Mono.just(output),new DefaultDataBufferFactory(),ResolvableType.forType(targetType),null,null));
}
