public void addChild(int x,int y,int colspan,int rowspan){
  Child child=new Child(childrens.size());
  LayoutParams layoutParams=new LayoutParams();
  layoutParams.rowSpec=new Spec(false,new Interval(y,y + rowspan),FILL,0.0f);
  layoutParams.columnSpec=new Spec(false,new Interval(x,x + colspan),FILL,0.0f);
  child.layoutParams=layoutParams;
  childrens.add(child);
  invalidateStructure();
}
