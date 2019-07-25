@Provides @LoadingScope Serializer<Series> series(@Named("common") SerializerFramework s){
  return new Series_Serializer(s);
}
