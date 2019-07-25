public IRDFaTriple[] parse(){
  try {
    this.aTransformer.transform(new StreamSource(this.in),new StreamResult(System.out));
  }
 catch (  final TransformerException e) {
    Data.logger.warn("Error while reading RDFa",e);
  }
  return this.allRDFaTriples.toArray(new IRDFaTriple[]{});
}
