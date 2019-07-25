@Override public Expression optimize(Session session){
  if (over != null) {
    over.optimize(session);
    ArrayList<SelectOrderBy> orderBy=over.getOrderBy();
    if (orderBy != null) {
      overOrderBySort=createOrder(session,orderBy,getNumExpressions());
    }
 else     if (!isAggregate()) {
      overOrderBySort=new SortOrder(session.getDatabase(),new int[getNumExpressions()],new int[0],null);
    }
    WindowFrame frame=over.getWindowFrame();
    if (frame != null) {
      int index=getNumExpressions();
      int orderBySize=0;
      if (orderBy != null) {
        orderBySize=orderBy.size();
        index+=orderBySize;
      }
      int n=0;
      WindowFrameBound bound=frame.getStarting();
      if (bound.isParameterized()) {
        checkOrderBy(frame.getUnits(),orderBySize);
        if (bound.isVariable()) {
          bound.setExpressionIndex(index);
          n++;
        }
      }
      bound=frame.getFollowing();
      if (bound != null && bound.isParameterized()) {
        checkOrderBy(frame.getUnits(),orderBySize);
        if (bound.isVariable()) {
          bound.setExpressionIndex(index + n);
          n++;
        }
      }
      numFrameExpressions=n;
    }
  }
  return this;
}
