public static List<Point2D.Double> get(Rectangle2D.Double rect){
  final List<Point2D.Double> result=new ArrayList<Point2D.Double>();
  result.add(new Point2D.Double(rect.x - rect.width,rect.y - rect.height));
  result.add(new Point2D.Double(rect.x,rect.y - rect.height));
  result.add(new Point2D.Double(rect.x + rect.width,rect.y - rect.height));
  result.add(new Point2D.Double(rect.x + 2 * rect.width,rect.y - rect.height));
  result.add(new Point2D.Double(rect.x - rect.width,rect.y));
  result.add(new Point2D.Double(rect.x + 2 * rect.width,rect.y));
  result.add(new Point2D.Double(rect.x - rect.width,rect.y + rect.height));
  result.add(new Point2D.Double(rect.x + 2 * rect.width,rect.y + rect.height));
  result.add(new Point2D.Double(rect.x - rect.width,rect.y + 2 * rect.height));
  result.add(new Point2D.Double(rect.x,rect.y + 2 * rect.height));
  result.add(new Point2D.Double(rect.x + rect.width,rect.y + 2 * rect.height));
  result.add(new Point2D.Double(rect.x + 2 * rect.width,rect.y + 2 * rect.height));
  return result;
}
