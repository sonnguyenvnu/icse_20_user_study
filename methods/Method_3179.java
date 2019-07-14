/** 
 * ??????
 * @param ndocs ????
 * @param docs  ???????
 */
void choose_smartly(int ndocs,List<Document> docs){
  int siz=size();
  double[] closest=new double[siz];
  if (siz < ndocs)   ndocs=siz;
  int index, count=0;
  index=random.nextInt(siz);
  docs.add(documents_.get(index));
  ++count;
  double potential=0.0;
  for (int i=0; i < documents_.size(); i++) {
    double dist=1.0 - SparseVector.inner_product(documents_.get(i).feature(),documents_.get(index).feature());
    potential+=dist;
    closest[i]=dist;
  }
  while (count < ndocs) {
    double randval=random.nextDouble() * potential;
    for (index=0; index < documents_.size(); index++) {
      double dist=closest[index];
      if (randval <= dist)       break;
      randval-=dist;
    }
    if (index == documents_.size())     index--;
    docs.add(documents_.get(index));
    ++count;
    double new_potential=0.0;
    for (int i=0; i < documents_.size(); i++) {
      double dist=1.0 - SparseVector.inner_product(documents_.get(i).feature(),documents_.get(index).feature());
      double min=closest[i];
      if (dist < min) {
        closest[i]=dist;
        min=dist;
      }
      new_potential+=min;
    }
    potential=new_potential;
  }
}
