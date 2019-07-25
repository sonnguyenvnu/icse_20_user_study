/** 
 * <p>Checks whether two collections are equal. Two collections C<sub>1</sub> and C<sub>2</sub> are equal, if the following conditions are true:</p> <ul> <li><p>For each c<sub>1<em>i</em></sub> (element of C<sub>1</sub>) there is a c<sub>2<em>j</em></sub> (element of C<sub>2</sub>), and c<sub>1<em>i</em></sub> equals c<sub>2<em>j</em></sub>.</p></li> <li><p>For each c<sub>2<em>i</em></sub> (element of C<sub>2</sub>) there is a c<sub>1<em>j</em></sub> (element of C<sub>1</sub>) and c<sub>2<em>i</em></sub> equals c<sub>1<em>j</em></sub>.</p></li> </ul>
 * @param c1 the first collection
 * @param c2 the second collection
 * @return <code>true</code> if the collections are equal, else<code>false</code>.
 */
public static boolean equals(Collection<?> c1,Collection<?> c2){
  Object[] o1=c1.toArray();
  Object[] o2=c2.toArray();
  return internalEquals(o1,o2);
}
