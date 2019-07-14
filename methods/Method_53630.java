public void screenToGraph(Point scr,Point2D graph){
  int draw_width=getWidth() - ins_left - ins_right;
  int draw_height=getHeight() - ins_top - ins_left;
  float x=minx + (maxx - minx) * ((scr.x - ins_left) / (float)draw_width);
  float y=miny + (maxy - miny) * ((ins_botom + draw_height - scr.y) / (float)draw_height);
  graph.setLocation(x,y);
}
