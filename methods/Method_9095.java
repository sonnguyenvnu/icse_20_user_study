static int adjust(int measureSpec,int delta){
  return makeMeasureSpec(MeasureSpec.getSize(measureSpec + delta),MeasureSpec.getMode(measureSpec));
}
