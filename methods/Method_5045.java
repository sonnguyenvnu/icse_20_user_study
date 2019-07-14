protected static DataSpec getCompressibleDataSpec(Uri uri){
  return new DataSpec(uri,0,C.LENGTH_UNSET,null,DataSpec.FLAG_ALLOW_GZIP);
}
