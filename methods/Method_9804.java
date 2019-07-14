public boolean chessCheck(int step){
  for (int i=0; i < this.chess.length; i++) {
    int count=0;
    for (int j=0; j < this.chess[i].length; j++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  for (int j=0; j < this.chess[0].length; j++) {
    int count=0;
    for (int i=0; i < this.chess.length; i++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  for (int x=0, y=0; x < this.chess.length; x++) {
    int count=0;
    for (int i=x, j=y; i < this.chess.length; i++, j++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  for (int x=0, y=0; y < this.chess[0].length; y++) {
    int count=0;
    for (int i=x, j=y; j < this.chess.length; i++, j++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  for (int x=0, y=0; x < this.chess.length; x++) {
    int count=0;
    for (int i=x, j=y; i >= 0; i--, j++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  for (int x=this.chess.length - 1, y=0; y < this.chess[0].length; y++) {
    int count=0;
    for (int i=x, j=y; j < this.chess.length; i--, j++) {
      if (this.chess[i][j] == step) {
        count++;
      }
 else       if (this.chess[i][j] != step && count < 5) {
        count=0;
      }
    }
    if (count >= 5) {
      return true;
    }
  }
  return false;
}
