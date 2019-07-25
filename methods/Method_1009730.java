/** 
 * This method initializes canvas.
 * @return
 */
public void clear(){
  Path path=new Path();
  path.moveTo(0F,0F);
  path.addRect(0F,0F,1000F,1000F,Path.Direction.CCW);
  path.close();
  Paint paint=new Paint();
  paint.setColor(Color.parseColor("#303030"));
  paint.setStyle(Paint.Style.FILL);
  if (this.historyPointer == this.pathLists.size()) {
    this.pathLists.add(path);
    this.paintLists.add(paint);
    this.historyPointer++;
  }
 else {
    this.pathLists.set(this.historyPointer,path);
    this.paintLists.set(this.historyPointer,paint);
    this.historyPointer++;
    for (int i=this.historyPointer, size=this.paintLists.size(); i < size; i++) {
      this.pathLists.remove(this.historyPointer);
      this.paintLists.remove(this.historyPointer);
    }
  }
  this.text="";
  this.invalidate();
}
