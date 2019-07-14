private Point startPositionRelativeToEntity(EntityView entityView){
  final float offset=200.0f;
  if (entityView != null) {
    Point position=entityView.getPosition();
    return new Point(position.x + offset,position.y + offset);
  }
 else {
    final float minimalDistance=100.0f;
    Point position=centerPositionForEntity();
    while (true) {
      boolean occupied=false;
      for (int index=0; index < entitiesView.getChildCount(); index++) {
        View view=entitiesView.getChildAt(index);
        if (!(view instanceof EntityView))         continue;
        Point location=((EntityView)view).getPosition();
        float distance=(float)Math.sqrt(Math.pow(location.x - position.x,2) + Math.pow(location.y - position.y,2));
        if (distance < minimalDistance) {
          occupied=true;
        }
      }
      if (!occupied)       break;
 else       position=new Point(position.x + offset,position.y + offset);
    }
    return position;
  }
}
