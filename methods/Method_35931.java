public boolean hasTransformer(AbstractTransformer transformer){
  return transformers != null && transformers.contains(transformer.getName());
}
