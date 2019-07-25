private static Coordinate[][][] compose(Edge[] edges,Edge[] holes,int numHoles){
  final List<List<Coordinate[]>> components=new ArrayList<>();
  assign(holes,holes(holes,numHoles),numHoles,edges(edges,numHoles,components),components);
  return buildCoordinates(components);
}
