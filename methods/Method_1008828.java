static int smear(int hashCode){
  return (int)(C2 * Integer.rotateLeft((int)(hashCode * C1),15));
}
