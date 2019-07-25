public SegmentNode clone() throws CloneNotSupportedException {
  SegmentNode node=new SegmentNode();
  node.isKey=this.isKey;
  node.value=this.value;
  return node;
}
