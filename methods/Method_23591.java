/** 
 * Randomize the list order using the random() function from the specified sketch, allowing shuffle() to use its current randomSeed() setting.
 */
public void shuffle(PApplet sketch){
  int num=count;
  while (num > 1) {
    int value=(int)sketch.random(num);
    num--;
    double temp=data[num];
    data[num]=data[value];
    data[value]=temp;
  }
}
