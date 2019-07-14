@SuppressWarnings("PMD.MissingBreakInSwitch") @Override public AxisIterator iterateAxis(byte axisNumber){
switch (axisNumber) {
case Axis.ANCESTOR:
    return new Navigator.AncestorEnumeration(this,false);
case Axis.ANCESTOR_OR_SELF:
  return new Navigator.AncestorEnumeration(this,true);
case Axis.ATTRIBUTE:
return new AttributeAxisIterator(this);
case Axis.CHILD:
if (children == null) {
return EmptyIterator.getInstance();
}
 else {
return new NodeArrayIterator(children);
}
case Axis.DESCENDANT:
return new Navigator.DescendantEnumeration(this,false,true);
case Axis.DESCENDANT_OR_SELF:
return new Navigator.DescendantEnumeration(this,true,true);
case Axis.FOLLOWING:
return new Navigator.FollowingEnumeration(this);
case Axis.FOLLOWING_SIBLING:
if (parent == null || siblingPosition == parent.children.length - 1) {
return EmptyIterator.getInstance();
}
 else {
return new NodeArrayIterator(parent.children,siblingPosition + 1,parent.children.length);
}
case Axis.NAMESPACE:
return super.iterateAxis(axisNumber);
case Axis.PARENT:
return SingleNodeIterator.makeIterator(parent);
case Axis.PRECEDING:
return new Navigator.PrecedingEnumeration(this,false);
case Axis.PRECEDING_SIBLING:
if (parent == null || siblingPosition == 0) {
return EmptyIterator.getInstance();
}
 else {
return new NodeArrayIterator(parent.children,0,siblingPosition);
}
case Axis.SELF:
return SingleNodeIterator.makeIterator(this);
case Axis.PRECEDING_OR_ANCESTOR:
return new Navigator.PrecedingEnumeration(this,true);
default :
return super.iterateAxis(axisNumber);
}
}
