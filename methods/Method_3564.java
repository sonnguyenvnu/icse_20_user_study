public void updateFrom(Node from){
  double weight=from.weight + MathUtility.calculateWeight(from.vertex,this.vertex);
  if (this.from == null || this.weight > weight) {
    this.from=from;
    this.weight=weight;
  }
}
