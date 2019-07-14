/** 
 * Given an index in the range <code>0...size()-1</code>, returns the value from the <code>index </code>th key-value mapping that this SparseFloatArray stores. <p>The values corresponding to indices in ascending order are guaranteed to be associated with keys in ascending order, e.g., <code>valueAt(0)</code> will return the value associated with the smallest key and <code>valueAt(size()-1)</code> will return the value associated with the largest key.
 */
public float valueAt(int index){
  return mValues[index];
}
