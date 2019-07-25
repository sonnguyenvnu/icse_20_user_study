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
package jetbrains.mps.text;

import jetbrains.mps.text.TextAreaFactory;
import jetbrains.mps.text.impl.TextAreaImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;

/**
 * Standard supplier of {@link TextArea}, suitable for most uses.
 * @author Artem Tikhomirov
 * @since 3.3
 */
@Immutable
public final class BasicTextAreaFactory implements TextAreaFactory{
  private final String myLineSeparator;
  private final int myIndentSize;
  private final char myIndentChar;
  private final int myDefaultBufferSize;

  public BasicTextAreaFactory() {
    this(System.getProperty("line.separator"), 2048, ' ', 2);
  }
  public BasicTextAreaFactory(@NotNull String lineSeparator, int defaultBufferSize, char indentChar, int indentSize) {
    myLineSeparator = lineSeparator;
    myDefaultBufferSize = defaultBufferSize;
    myIndentChar = indentChar;
    myIndentSize = indentSize;
  }

  @NotNull
  @Override
  public TextArea create() {
    return new TextAreaImpl(new StringBuilder(myDefaultBufferSize), myLineSeparator, myIndentChar, myIndentSize);
  }

  @NotNull
  @Override
  public String getLineSeparator() {
    return myLineSeparator;
  }

  @NotNull
  @Override
  public CharSequence value(@NotNull TextArea area) {
    return ((TextAreaImpl) area).value();
  }
}
