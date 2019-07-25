private static List<Integer> analyze(Shape shape){
  int count=PathIteratorLimited.count(shape);
  final List<Integer> closings=getClosings(shape.getPathIterator(null));
  final List<Integer> result=new ArrayList<Integer>();
  for (  Integer cl : closings) {
    if (cl + 2 >= count) {
      break;
    }
    final PathIterator path1=new PathIteratorLimited(shape,0,cl);
    final PathIterator path2=new PathIteratorLimited(shape,cl + 1,Integer.MAX_VALUE);
    final double max1=getMinMax(0,0,path1).getMaxX();
    final double min2=getMinMax(0,0,path2).getMinX();
    if (min2 > max1) {
      result.add(cl);
    }
  }
  return result;
}
