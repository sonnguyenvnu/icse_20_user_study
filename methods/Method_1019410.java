/** 
 * Replace the current list of transactions with these. A null argument is treated as an empty list.
 */
public void reset(Queue<app.intra.net.doh.Transaction> transactions){
  this.transactions.clear();
  if (transactions != null) {
    for (    app.intra.net.doh.Transaction t : transactions) {
      this.transactions.add(new Transaction(t));
    }
  }
 else {
    countryMap=null;
  }
  this.notifyDataSetChanged();
}
