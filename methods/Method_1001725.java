public Dimension2D solve(Cluster root,Collection<Path> paths) throws IOException {
  final String dotString=new DotxMaker(root,paths).createDotString("nodesep=0.2;","ranksep=0.2;");
  final MinFinder minMax=new MinFinder();
  final Graphviz graphviz=GraphvizUtils.create(null,dotString,"svg");
  final ByteArrayOutputStream baos=new ByteArrayOutputStream();
  final ProcessState state=graphviz.createFile3(baos);
  baos.close();
  if (state.differs(ProcessState.TERMINATED_OK())) {
    throw new IllegalStateException("Timeout2 " + state);
  }
  final byte[] result=baos.toByteArray();
  final String s=new String(result,"UTF-8");
  final Pattern pGraph=Pattern.compile("(?m)\\<svg\\s+width=\"(\\d+)pt\"\\s+height=\"(\\d+)pt\"");
  final Matcher mGraph=pGraph.matcher(s);
  if (mGraph.find() == false) {
    throw new IllegalStateException();
  }
  final int width=Integer.parseInt(mGraph.group(1));
  final int height=Integer.parseInt(mGraph.group(2));
  final YDelta yDelta=new YDelta(height);
  for (  Block b : root.getRecursiveContents()) {
    final String start="b" + b.getUid();
    final int p1=s.indexOf("<title>" + start + "</title>");
    if (p1 == -1) {
      throw new IllegalStateException();
    }
    final List<Point2D.Double> pointsList=extractPointsList(s,p1,yDelta);
    b.setX(getMinX(pointsList));
    b.setY(getMinY(pointsList));
    minMax.manage(b.getPosition());
  }
  for (  Cluster cl : root.getSubClusters()) {
    final String start="cluster" + cl.getUid();
    final int p1=s.indexOf("<title>" + start + "</title>");
    if (p1 == -1) {
      throw new IllegalStateException();
    }
    final List<Point2D.Double> pointsList=extractPointsList(s,p1,yDelta);
    cl.setX(getMinX(pointsList));
    cl.setY(getMinY(pointsList));
    final double w=getMaxX(pointsList) - getMinX(pointsList);
    final double h=getMaxY(pointsList) - getMinY(pointsList);
    cl.setHeight(h);
    cl.setWidth(w);
    minMax.manage(cl.getPosition());
  }
  for (  Path p : paths) {
    final String start="b" + p.getStart().getUid();
    final String end="b" + p.getEnd().getUid();
    final String searched="<title>" + start + "&#45;&gt;" + end + "</title>";
    final int p1=s.indexOf(searched);
    if (p1 == -1) {
      throw new IllegalStateException(searched);
    }
    final int p2=s.indexOf(" d=\"",p1);
    final int p3=s.indexOf("\"",p2 + " d=\"".length());
    final String points=s.substring(p2 + " d=\"".length(),p3);
    final DotPath dotPath=new DotPath(new SvgResult(points,yDelta));
    p.setDotPath(dotPath);
    minMax.manage(dotPath.getMinFinder());
    if (p.getLabel() != null) {
      final List<Point2D.Double> pointsList=extractPointsList(s,p1,yDelta);
      final double x=getMinX(pointsList);
      final double y=getMinY(pointsList);
      p.setLabelPosition(x,y);
      minMax.manage(x,y);
    }
  }
  return new Dimension2DDouble(width,height);
}
