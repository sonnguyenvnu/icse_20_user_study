public static Observable<DiscoveryParams> params(final @NonNull Intent intent,final @NonNull ApiClientType client){
  final Observable<DiscoveryParams> paramsFromParcel=Observable.just(paramsFromIntent(intent)).filter(ObjectUtils::isNotNull);
  final Observable<DiscoveryParams> paramsFromUri=Observable.just(IntentMapper.uri(intent)).filter(ObjectUtils::isNotNull).map(DiscoveryParams::fromUri).flatMap(uri -> paramsFromUri(uri,client));
  return Observable.merge(paramsFromParcel,paramsFromUri);
}
