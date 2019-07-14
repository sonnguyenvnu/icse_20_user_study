static Factory provider(Context context){
  return (log,compilation) -> new JavacErrorDescriptionListener(log,compilation.endPositions,compilation.getSourceFile(),context,false);
}
