public Iterator<Point2D.Double> iterator(){
  return Collections.unmodifiableCollection(points).iterator();
}
