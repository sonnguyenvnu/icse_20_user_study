public static void main(String args[]){
  Do_Not_Terminate.forbidExit();
  try {
    Scanner sc=new Scanner(System.in);
    FoodFactory foodFactory=new FoodFactory();
    Food food=foodFactory.getFood(sc.nextLine());
    System.out.println("The factory returned " + food.getClass());
    System.out.println(food.getType());
  }
 catch (  Do_Not_Terminate.ExitTrappedException e) {
    System.out.println("Unsuccessful Termination!!");
  }
}
