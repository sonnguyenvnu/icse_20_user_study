public List<LineSegmentInt> inflate(Collection<LineSegmentInt> segments){
  final List<LineSegmentInt> result=new ArrayList<LineSegmentInt>();
  LineSegmentInt last=null;
  final Collection<LineSegmentInt> cutSegments=cutSegments(segments);
  for (  LineSegmentInt seg : inflateSegmentCollection(cutSegments)) {
    if (last != null && last.getP2().equals(seg.getP1()) == false) {
      result.add(new LineSegmentInt(last.getP2(),seg.getP1()));
    }
    result.add(seg);
    last=seg;
  }
  return result;
}
