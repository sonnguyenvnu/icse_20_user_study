public int length(){
  return 1 + subForest._1().map(Tree::length).foldLeft((acc,i) -> acc + i,0);
}
