public void updateFrom(Vertex from){
  double weight=from.weight + MathUtility.calculateWeight(from,this);
  if (this.from == null || this.weight > weight) {
    this.from=from;
    this.weight=weight;
  }
}
