/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.text.impl;

import jetbrains.mps.text.TextArea;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Artem Tikhomirov
 */
public class TextAreaImpl implements TextArea {
  private final StringBuilder myStringBuilder;
  private final String myLineSeparator;
  private final int myIndentSize;
  private int myDepth = 0;
  private final char[] mySpaces;

  public TextAreaImpl() {
    this(new StringBuilder(2048), "\n", ' ', 2);
  }

  public TextAreaImpl(@NotNull StringBuilder stringBuilder, @NotNull String lineSeparator, char indentChar, int indentSize) {
    assert indentSize >= 0;
    myStringBuilder = stringBuilder;
    myIndentSize = indentSize;
    myLineSeparator = lineSeparator;
    mySpaces = new char[indentSize * 20];
    Arrays.fill(mySpaces, indentChar);
  }


  @Override
  public TextArea append(CharSequence text) {
    // XXX mimic TextGenBuffer.append(String) convention. I'm not 100% sure it is correct to silently skip nulls
    if (text != null) {
      myStringBuilder.append(text);
    }
    return this;
  }

  @Override
  public TextArea newLine() {
    myStringBuilder.append(myLineSeparator);
    return this;
  }

  @Override
  public TextArea indent() {
    int x = myDepth * myIndentSize;
    while (x > 0) {
      int c = Math.min(x, mySpaces.length);
      myStringBuilder.append(mySpaces, 0, c);
      x -= c;
    }
    return this;
  }

  @Override
  public TextArea increaseIndent() {
    myDepth++;
    return this;
  }

  @Override
  public TextArea decreaseIndent() {
    myDepth--;
    assert myDepth >= 0;
    return this;
  }

  @Override
  public int length() {
    return myStringBuilder.length();
  }

  /**
   * XXX not quite sure yet it should be part of TextArea API or not
   * @return actual contents of the text chunk.
   */
  @NotNull
  public CharSequence value() {
    return myStringBuilder;
  }
}
