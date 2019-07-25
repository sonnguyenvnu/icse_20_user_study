/** 
 * Initializes the TableLayout for all constructors.
 * @param col widths of columns in the format, {{col0, col1, col2, ..., colN}
 * @param row heights of rows in the format, {{row0, row1, row2, ..., rowN}
 */
protected void init(double[] col,double[] row){
  if (col == null) {
    throw new IllegalArgumentException("Parameter col cannot be null");
  }
  if (row == null) {
    throw new IllegalArgumentException("Parameter row cannot be null");
  }
  crSpec[C]=new double[col.length];
  crSpec[R]=new double[row.length];
  System.arraycopy(col,0,crSpec[C],0,crSpec[C].length);
  System.arraycopy(row,0,crSpec[R],0,crSpec[R].length);
  for (int counter=0; counter < crSpec[C].length; counter++) {
    if ((crSpec[C][counter] < 0.0) && (crSpec[C][counter] != FILL) && (crSpec[C][counter] != PREFERRED) && (crSpec[C][counter] != MINIMUM)) {
      crSpec[C][counter]=0.0;
    }
  }
  for (int counter=0; counter < crSpec[R].length; counter++) {
    if ((crSpec[R][counter] < 0.0) && (crSpec[R][counter] != FILL) && (crSpec[R][counter] != PREFERRED) && (crSpec[R][counter] != MINIMUM)) {
      crSpec[R][counter]=0.0;
    }
  }
  list=new LinkedList();
  dirty=true;
}
