/** 
 * Replaces notes
 */
public void replace(Note note,int index){
  if (notes.indexOf(note) != -1) {
    notes.remove(index);
  }
 else {
    index=notes.size();
  }
  notes.add(index,note);
}
