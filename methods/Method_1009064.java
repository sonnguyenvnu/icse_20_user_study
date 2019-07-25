/** 
 * Writes the diff sequence using the specified formatter.
 * @param formatter The formatter that will handle the output.
 * @throws IOException If thrown by the formatter.
 */
public void process(DiffXFormatter formatter) throws IOException {
  processEmpty(formatter);
  if (this.length1 == 0 || this.length2 == 0)   return;
  length();
  int i=0;
  int j=0;
  DiffXEvent e1=this.sequence1.getEvent(i);
  DiffXEvent e2=this.sequence2.getEvent(j);
  while (i < this.length1 && j < this.length2) {
    e1=this.sequence1.getEvent(i);
    e2=this.sequence2.getEvent(j);
    if (this.matrix.isGreaterX(i,j)) {
      if (this.estate.okInsert(e1) && !this.estate.hasPriorityOver(e2,e1)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + j + "] >i +" + ShortStringFormatter.toShortString(e1));
        }
        formatter.insert(e1);
        this.estate.insert(e1);
        i++;
      }
 else       if (e1.equals(e2) && this.estate.okFormat(e1)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + (j + 1) + "] >f " + ShortStringFormatter.toShortString(e1));
        }
        formatter.format(e1);
        this.estate.format(e1);
        i++;
        j++;
      }
 else       if (this.estate.okDelete(e2)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + i + "," + (j + 1) + "] >d -" + ShortStringFormatter.toShortString(e2));
        }
        formatter.delete(e2);
        this.estate.delete(e2);
        j++;
      }
 else {
        if (DEBUG) {
          System.err.print("\n(i) case greater X");
        }
        if (DEBUG) {
          printLost(i,j);
        }
        break;
      }
    }
 else     if (this.matrix.isGreaterY(i,j)) {
      if (this.estate.okDelete(e2) && !this.estate.hasPriorityOver(e1,e2)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + i + "," + (j + 1) + "] <d -" + ShortStringFormatter.toShortString(e2));
        }
        formatter.delete(e2);
        this.estate.delete(e2);
        j++;
      }
 else       if (e1.equals(e2) && this.estate.okFormat(e1)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + (j + 1) + "] <f " + ShortStringFormatter.toShortString(e1));
        }
        formatter.format(e1);
        this.estate.format(e1);
        i++;
        j++;
      }
 else       if (this.estate.okInsert(e1)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + j + "] <i +" + ShortStringFormatter.toShortString(e1));
        }
        formatter.insert(e1);
        this.estate.insert(e1);
        i++;
      }
 else {
        if (DEBUG) {
          System.err.println("\n(i) case greater Y");
        }
        if (DEBUG) {
          printLost(i,j);
        }
        break;
      }
    }
 else     if (this.matrix.isSameXY(i,j)) {
      if (e1.equals(e2) && this.estate.okFormat(e1)) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + (j + 1) + "] =f " + ShortStringFormatter.toShortString(e1));
        }
        formatter.format(e1);
        this.estate.format(e1);
        i++;
        j++;
      }
 else       if (this.estate.okInsert(e1) && !(e2 instanceof AttributeEvent && !(e1 instanceof AttributeEvent))) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + (i + 1) + "," + j + "] =i +" + ShortStringFormatter.toShortString(e1));
        }
        this.estate.insert(e1);
        formatter.insert(e1);
        i++;
      }
 else       if (this.estate.okDelete(e2) && !(e1 instanceof AttributeEvent && !(e2 instanceof AttributeEvent))) {
        if (DEBUG) {
          System.err.print("[" + i + "," + j + "]->[" + i + "," + (j + 1) + "] =d -" + ShortStringFormatter.toShortString(e2));
        }
        formatter.delete(e2);
        this.estate.delete(e2);
        j++;
      }
 else {
        if (DEBUG) {
          System.err.println("\n(i) case same");
        }
        if (DEBUG) {
          printLost(i,j);
        }
        break;
      }
    }
 else {
      if (DEBUG) {
        System.err.println("\n(i) case ???");
      }
      if (DEBUG) {
        printLost(i,j);
      }
      break;
    }
    if (DEBUG) {
      System.err.println("    stack:" + this.estate.currentChange() + ShortStringFormatter.toShortString(this.estate.current()));
    }
  }
  while (i < this.length1) {
    if (DEBUG) {
      System.err.println("[" + i + "," + j + "]->[" + (i + 1) + "," + j + "] _i -" + ShortStringFormatter.toShortString(this.sequence1.getEvent(i)));
    }
    this.estate.insert(this.sequence1.getEvent(i));
    formatter.insert(this.sequence1.getEvent(i));
    i++;
  }
  while (j < this.length2) {
    if (DEBUG) {
      System.err.println("[" + i + "," + j + "]->[" + i + "," + (j + 1) + "] _d -" + ShortStringFormatter.toShortString(this.sequence2.getEvent(j)));
    }
    this.estate.delete(this.sequence2.getEvent(j));
    formatter.delete(this.sequence2.getEvent(j));
    j++;
  }
}
