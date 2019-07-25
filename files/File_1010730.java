/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.cellMenu;

import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;

import java.awt.Color;
import java.util.Optional;

public class EditorMenuItemStyleImpl implements EditorMenuItemStyle {
  private boolean myIsVisible = true;
  private boolean myBold;
  private Priority myPriority = new Priority();
  private Color myBackgroundColor;
  private Color myTextColor;
  private boolean myItalic;
  private boolean myStrikeout;
  private boolean myWasCustomized;
  private String myDescriptionText;

  @Override
  public void hide() {
    myIsVisible = false;
    myWasCustomized = true;
  }

  @Override
  public void setPriority(double priority) {
    myPriority.setPriority(priority);
    myWasCustomized = true;
  }

  @Override
  public void setBold() {
    myBold = true;
    myWasCustomized = true;
  }

  @Override
  public void setItalic() {
    myItalic = true;
    myWasCustomized = true;
  }

  @Override
  public void setStrikeout() {
    myStrikeout = true;
    myWasCustomized = true;
  }

  @Override
  public void setBackgroundColor(Color color) {
    myBackgroundColor = color;
    myWasCustomized = true;
  }

  @Override
  public void setTextColor(Color color) {
    myTextColor = color;
    myWasCustomized = true;
  }

  @Override
  public void setDescriptionText(String descriptionText) {
    myDescriptionText = descriptionText;
    myWasCustomized = true;
  }

  public boolean isVisible() {
    return myIsVisible;
  }

  public boolean isBold() {
    return myBold;
  }

  public boolean isItalic() {
    return myItalic;
  }

  public boolean isStrikeout() {
    return myStrikeout;
  }

  public double getPriority() {
    return myPriority.getPriority();
  }

  public Optional<Color> getBackgroundColor() {
    return Optional.ofNullable(myBackgroundColor);
  }

  public Optional<Color> getTextColor() {
    return Optional.ofNullable(myTextColor);
  }

  public Optional<String> getDescriptionText() {
    return Optional.ofNullable(myDescriptionText);
  }

  boolean wasCustomized() {
    return myWasCustomized;
  }

  private static class Priority {
    private double myPriority;
    private boolean wasExplicitlyCustomized;

    private void setPriority(double priority) {
      myPriority = wasExplicitlyCustomized ? Math.max(priority, myPriority) : priority;
      wasExplicitlyCustomized = true;
    }

    private double getPriority() {
      return myPriority;
    }
  }
}
