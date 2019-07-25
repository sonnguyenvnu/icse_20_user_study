/*
 * Copyright 2003-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.smodel.persistence.def;

public class XmlFastScanner {
  public static final int EOI = 0;
  public static final int SIMPLE_TAG = 1;
  public static final int OPEN_TAG = 2;
  public static final int CLOSE_TAG = 3;
  public static final int OTHER = 4;

  private int tokenOffset;
  private int currOffset;
  private String name;
  private int currDepth = 0;
  private int lastTag = -1; // remember whether we yielded SIMPLE_TAG or OPEN_TAG to decrease currDepth accordingly

  final private char[] data;
  private char chr;

  public XmlFastScanner(char[] data) {
    this.data = data;
    reset();
  }

  private XmlFastScanner reset() {
    this.currOffset = 0;
    chr = data.length > 0 ? data[0] : 0;
    return this;
  }

  private void shift() {
    if (currOffset < data.length) {
      currOffset++;
    }
    chr = currOffset < data.length ? data[currOffset] : 0;
  }

  private void skipSpaces() {
    while (chr != 0 && Character.isWhitespace(chr)) {
      shift();
    }
  }

  private void skipName() {
    assert Character.isJavaIdentifierStart(chr);
    int start = currOffset;
    shift();
    while (chr != 0 && Character.isJavaIdentifierPart(chr)) {
      shift();
    }
    name = new String(data, start, currOffset - start);
  }

  public int next() {
    tokenOffset = currOffset;
    name = null;
    if (lastTag == OPEN_TAG) {
      currDepth++;
    }
    lastTag = -1;
    if (chr == '<') {
      shift();
      skipSpaces();
      if (Character.isJavaIdentifierStart(chr)) {
        skipName();

        while (chr != 0 && chr != '>') {
          shift();
        }
        if (chr == '>') {
          int slashIndex = currOffset - 1;
          shift();
          while (slashIndex > tokenOffset && Character.isWhitespace(data[slashIndex])) {
            slashIndex--;
          }
          return lastTag = data[slashIndex] == '/' ? SIMPLE_TAG : OPEN_TAG;
        }
        return OTHER;
      } else if (chr == '/') {
        shift();
        skipSpaces();
        if (Character.isJavaIdentifierStart(chr)) {
          skipName();
          while (chr != 0 && chr != '>') {
            shift();
          }
          if (chr == '>') {
            shift();
            currDepth--;
            return lastTag = CLOSE_TAG;
          }
        }
        return OTHER;
      }

    }
    if (chr != 0) {
      while (chr != 0 && chr != '<') {
        shift();
      }
      return OTHER;
    }

    return EOI;
  }

  /**
   * 0-based (top xml element has depth == 0) value indicating nesting of a tag. Meaningless for tokens other than OPEN_TAG, CLOSE_TAG, SIMPLE_TAG.
   */
  public int tagDepth() {
    return currDepth;
  }

  public String token() {
    return new String(data, tokenOffset, currOffset - tokenOffset);
  }

  public String getText(int start, int end) {
    return new String(data, start, end - start);
  }

  public int getTokenOffset() {
    return tokenOffset;
  }

  public int getOffset() {
    return currOffset;
  }

  public String getName() {
    return name;
  }

  public static void main(String[] args) {
    XmlFastScanner s = new XmlFastScanner("<l1 a=1><l2 b=2><l3 c=3/><l3/><l3/></l2><l2><l3></l3></l2></l1>".toCharArray());
    assertNextTag(s, OPEN_TAG, 0);//  <l1>
    assertNextTag(s, OPEN_TAG, 1);//   <l2>
    assertNextTag(s, SIMPLE_TAG, 2);//   <l3/>
    assertNextTag(s, SIMPLE_TAG, 2);//   <l3/>
    assertNextTag(s, SIMPLE_TAG, 2);//   <l3/>
    assertNextTag(s, CLOSE_TAG, 1);//  </l2>
    assertNextTag(s, OPEN_TAG, 1);//   <l2>
    assertNextTag(s, OPEN_TAG, 2);//     <l3>
    assertNextTag(s, CLOSE_TAG, 2);//    </l3>
    assertNextTag(s, CLOSE_TAG, 1);//  </l2>
    assertNextTag(s, CLOSE_TAG, 0);// </l1>
    print(s.reset());
  }
  private static void assertNextTag(XmlFastScanner s, int tagKind, int depth) {
    if (s.next() != tagKind) {
      throw new IllegalStateException();
    }
    if (s.tagDepth() != depth) {
      throw new IllegalStateException();
    }
  }
  private static void print(XmlFastScanner s) {
    int token;
    while ((token = s.next()) != EOI) {
      if (token == SIMPLE_TAG || token == OPEN_TAG || token == CLOSE_TAG) {
        for (int i = s.tagDepth(); i > 0; i--) {
          System.out.print("  ");
        }
        System.out.println(s.getName());
      }
    }
  }
}
