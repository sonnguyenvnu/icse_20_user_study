@Override public AxisIterator iterateAxis(byte axisNumber){
switch (axisNumber) {
case Axis.DESCENDANT:
    return new Navigator.DescendantEnumeration(this,false,true);
case Axis.DESCENDANT_OR_SELF:
  return new Navigator.DescendantEnumeration(this,true,true);
case Axis.CHILD:
return SingleNodeIterator.makeIterator(rootNode);
default :
return super.iterateAxis(axisNumber);
}
}
