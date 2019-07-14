@Override public RowFilter getRowFilter(Project project){
  if (config.isSelected() && eval_x != null && errorMessage_x == null && eval_y != null && errorMessage_y == null) {
    return new DualExpressionsNumberComparisonRowFilter(eval_x,config.columnName_x,columnIndex_x,eval_y,config.columnName_y,columnIndex_y){
      @Override protected boolean checkValues(      double x,      double y){
        Point2D.Double p=new Point2D.Double(x,y);
        p=translateCoordinates(p,min_x,max_x,min_y,max_y,config.dim_x,config.dim_y,config.l,t);
        return p.x >= from_x_pixels && p.x <= to_x_pixels && p.y >= from_y_pixels && p.y <= to_y_pixels;
      }
    }
;
  }
 else {
    return null;
  }
}
