/*
 * Copyright 2003-2017 JetBrains s.r.o.
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

import jetbrains.mps.text.BasicToken;
import jetbrains.mps.text.BufferLayout;
import jetbrains.mps.text.BufferSnapshot;
import jetbrains.mps.text.TextAreaToken;
import jetbrains.mps.text.TextBuffer;
import jetbrains.mps.text.TextMark;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Prepare {@link BufferLayoutConfiguration}.
 * @author Artem Tikhomirov
 */
public class BufferLayoutBuilder {
  private final Set<TextAreaToken> myRootTokens = new LinkedHashSet<>();
  private final Map<TextAreaToken,TextAreaToken> myChildToParentTokens = new LinkedHashMap<>();
  private TextAreaToken myInitial;

  public BufferLayoutBuilder add(@NotNull Object tokenIdentity) {
    if (!myRootTokens.add(new BasicToken(tokenIdentity))) {
      throw new IllegalArgumentException("Duplicated text area token:" + tokenIdentity);
    }
    return this;
  }

  public BufferLayoutBuilder add(@NotNull Object parentTokenIdentity, @NotNull Object childTokenIdentity) {
    if (!myRootTokens.contains(new BasicToken(parentTokenIdentity))) {
      throw new IllegalArgumentException("Unknown parent text area token:" + parentTokenIdentity);
    }
    BasicToken childToken = new BasicToken(childTokenIdentity);
    if (myChildToParentTokens.containsKey(childToken)) {
      throw new IllegalArgumentException("Child is already registered with another parent:" + myChildToParentTokens.get(childToken));
    }
    myChildToParentTokens.put(childToken, new BasicToken(parentTokenIdentity));
    return this;
  }

  public BufferLayoutBuilder activate(Object tokenIdentity) {
    BasicToken token = new BasicToken(tokenIdentity);
    if (!myRootTokens.contains(token) && !myChildToParentTokens.containsKey(token)) {
      throw new IllegalArgumentException("Can't activate unknown token:" + tokenIdentity);
    }
    myInitial = token;
    return this;
  }


  public BufferLayoutConfiguration create() {
    return new BufferLayoutConfiguration() {
      private BufferLayout myLayout; // optional

      @Override
      public void prepareBuffer(@NotNull TextBuffer buffer) {
        for (TextAreaToken t : myRootTokens) {
          buffer.pushTextArea(t).popTextArea();
        }
        if (!myChildToParentTokens.isEmpty()) {
          myLayout = buffer.newLayout();
          for (Entry<TextAreaToken, TextAreaToken> p : myChildToParentTokens.entrySet()) {
            // superfluous, but necessary at the moment (see TextBufferImpl#snapshot()) - to let buffer know about
            // any possible (event empty) text area
            buffer.pushTextArea(p.getKey()).popTextArea();
            TextMark childAreaLocationMark = buffer.pushTextArea(p.getValue()).pushMark().popMark();
            buffer.popTextArea();
            // child area goes into given location inside parent area
            myLayout.replace(childAreaLocationMark, p.getKey());
          }
        }

        if (myInitial != null) {
          buffer.pushTextArea(myInitial);
        }
        // Generally, myInitial shall be always set (we demand TextUnitLayout.active != null)
        // though chances are this BLB would be in use directly, not through TextGen's generator, and in this case we keep global, top-most area as active
      }

      @NotNull
      @Override
      public BufferSnapshot prepareSnapshot(@NotNull TextBuffer buffer) {
        if (myLayout != null) {
          return buffer.snapshot(myLayout);
        }
        return super.prepareSnapshot(buffer);
      }
    };
  }
}
