/** 
 * The recursive method called by innerDiff that diffs the subobjects of the variable value objects to indicate which rows of the hierarchical trace table should be highlighted to show the parts of the state that have changed.
 */
public void diff(TLCVariableValue other){
  if (!this.toSimpleString().equals(other.toSimpleString())) {
    other.setChanged();
    if (this.getClass().equals(other.getClass())) {
      innerDiff(other);
    }
  }
}
