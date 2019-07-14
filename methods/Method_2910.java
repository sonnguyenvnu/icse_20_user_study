/** 
 * ?bin??
 * @param byteArray
 * @return
 */
public boolean load(ByteArray byteArray){
  if (byteArray == null)   return false;
  model_header=byteArray.nextString();
  root=byteArray.nextString();
  use_distance=byteArray.nextInt() == 1;
  use_valency=byteArray.nextInt() == 1;
  use_cluster=byteArray.nextInt() == 1;
  W1=new Matrix();
  W1.load(byteArray);
  W2=new Matrix();
  W2.load(byteArray);
  E=new Matrix();
  E.load(byteArray);
  b1=new Matrix();
  b1.load(byteArray);
  saved=new Matrix();
  saved.load(byteArray);
  forms_alphabet=new Alphabet();
  forms_alphabet.load(byteArray);
  postags_alphabet=new Alphabet();
  postags_alphabet.load(byteArray);
  deprels_alphabet=new Alphabet();
  deprels_alphabet.load(byteArray);
  precomputation_id_encoder=read_map(byteArray);
  if (use_cluster) {
    cluster4_types_alphabet=new Alphabet();
    cluster4_types_alphabet.load(byteArray);
    cluster6_types_alphabet=new Alphabet();
    cluster6_types_alphabet.load(byteArray);
    cluster_types_alphabet=new Alphabet();
    cluster_types_alphabet.load(byteArray);
    form_to_cluster4=read_map(byteArray);
    form_to_cluster6=read_map(byteArray);
    form_to_cluster=read_map(byteArray);
  }
  assert !byteArray.hasMore() : "???????????????";
  classifier=new NeuralNetworkClassifier(W1,W2,E,b1,saved,precomputation_id_encoder);
  classifier.canonical();
  return true;
}
