/** 
 * ?????
 */
void canonical(){
  hidden_layer_size=b1.rows();
  nr_feature_types=W1.cols() / E.rows();
  nr_classes=W2.rows();
  embedding_size=E.rows();
}
