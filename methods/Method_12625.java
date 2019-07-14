public int compareTo(LexemePath o){
  if (this.payloadLength > o.payloadLength) {
    return -1;
  }
 else   if (this.payloadLength < o.payloadLength) {
    return 1;
  }
 else {
    if (this.size() < o.size()) {
      return -1;
    }
 else     if (this.size() > o.size()) {
      return 1;
    }
 else {
      if (this.getPathLength() > o.getPathLength()) {
        return -1;
      }
 else       if (this.getPathLength() < o.getPathLength()) {
        return 1;
      }
 else {
        if (this.pathEnd > o.pathEnd) {
          return -1;
        }
 else         if (pathEnd < o.pathEnd) {
          return 1;
        }
 else {
          if (this.getXWeight() > o.getXWeight()) {
            return -1;
          }
 else           if (this.getXWeight() < o.getXWeight()) {
            return 1;
          }
 else {
            if (this.getPWeight() > o.getPWeight()) {
              return -1;
            }
 else             if (this.getPWeight() < o.getPWeight()) {
              return 1;
            }
          }
        }
      }
    }
  }
  return 0;
}
