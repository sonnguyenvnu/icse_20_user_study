@Keep public void setThemeAnimationValue(float value){
  themeAnimationValue=value;
  for (int j=0; j < 2; j++) {
    if (themeAnimatorDescriptions[j] != null) {
      int rE, gE, bE, aE, rS, gS, bS, aS, a, r, g, b;
      for (int i=0; i < themeAnimatorDescriptions[j].length; i++) {
        rE=Color.red(animateEndColors[j][i]);
        gE=Color.green(animateEndColors[j][i]);
        bE=Color.blue(animateEndColors[j][i]);
        aE=Color.alpha(animateEndColors[j][i]);
        rS=Color.red(animateStartColors[j][i]);
        gS=Color.green(animateStartColors[j][i]);
        bS=Color.blue(animateStartColors[j][i]);
        aS=Color.alpha(animateStartColors[j][i]);
        a=Math.min(255,(int)(aS + (aE - aS) * value));
        r=Math.min(255,(int)(rS + (rE - rS) * value));
        g=Math.min(255,(int)(gS + (gE - gS) * value));
        b=Math.min(255,(int)(bS + (bE - bS) * value));
        int color=Color.argb(a,r,g,b);
        Theme.setAnimatedColor(themeAnimatorDescriptions[j][i].getCurrentKey(),color);
        themeAnimatorDescriptions[j][i].setColor(color,false,false);
      }
      if (themeAnimatorDelegate[j] != null) {
        themeAnimatorDelegate[j].didSetColor();
      }
    }
  }
}
