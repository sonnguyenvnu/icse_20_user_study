/** 
 * ????
 * @param vertexList
 */
protected static void speechTagging(List<Vertex> vertexList){
  Viterbi.compute(vertexList,CoreDictionaryTransformMatrixDictionary.transformMatrixDictionary);
}
