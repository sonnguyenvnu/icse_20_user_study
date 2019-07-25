public static void create(Queue<Bubble> bubbles,View view,Bitmap bitmap){
  if (bubbles.size() < 60) {
    for (int i=0; i < 3; i++) {
      bubbles.add(createBubble(view,bitmap));
    }
  }
}
