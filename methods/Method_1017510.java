public void bind(int position){
switch (position % 6) {
case 0:
    image.setImageResource(R.drawable.placekitten_1);
  break;
case 1:
image.setImageResource(R.drawable.placekitten_2);
break;
case 2:
image.setImageResource(R.drawable.placekitten_3);
break;
case 3:
image.setImageResource(R.drawable.placekitten_4);
break;
case 4:
image.setImageResource(R.drawable.placekitten_5);
break;
case 5:
image.setImageResource(R.drawable.placekitten_6);
break;
}
ViewCompat.setTransitionName(image,position + "_image");
image.setOnClickListener(v -> {
int kittenNumber=(position % 6) + 1;
ViewCompat.setTransitionName(image,position + "_image");
DetailsKey detailsKey=DetailsKey.create(SharedElement.create(ViewCompat.getTransitionName(image),"kittenImage"),kittenNumber);
MainActivity.get(v.getContext()).getBackstack().goTo(detailsKey);
}
);
}
