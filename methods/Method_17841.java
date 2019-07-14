InternalNode newLayoutBuilder(@AttrRes int defStyleAttr,@StyleRes int defStyleRes){
  final InternalNode node=InternalNodeUtils.create(this);
  applyStyle(node,defStyleAttr,defStyleRes);
  return node;
}
