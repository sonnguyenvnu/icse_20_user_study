/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.textgen.trace;

import jetbrains.mps.util.InternUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.Comparator;
import java.util.Objects;

public abstract class PositionInfo implements Comparable<PositionInfo> {
  private String myFileName;
  private String myNodeId;
  private int myStartLine;
  private int myStartPosition;
  private int myEndLine;
  private int myEndPosition;

  public PositionInfo() {
  }

  @Nullable
  public String getFileName() {
    return myFileName;
  }

  @Nullable
  public String getNodeId() {
    return myNodeId;
  }

  /**
   * Replacement for <code>getNodeId().equals(nodePointer.getNodeId().toString())</code>.
   * @param nodeId tolerates null
   * @return <code>true</code> iff is the same as the one associated with this position.
   */
  public boolean matches(@Nullable SNodeId nodeId) {
    return Objects.equals(myNodeId, nodeId == null ? null : nodeId.toString());
  }

  public int getStartLine() {
    return myStartLine;
  }

  public int getStartPosition() {
    return myStartPosition;
  }

  public int getEndLine() {
    return myEndLine;
  }

  public int getEndPosition() {
    return myEndPosition;
  }

  public void setFileName(String fileName) {
    myFileName = InternUtil.intern(fileName);
  }

  public void setNodeId(@NonNls String nodeId) {
    myNodeId = InternUtil.intern(nodeId);
  }

  public void setStartLine(int startLine) {
    myStartLine = startLine;
  }

  public void setStartPosition(int startPosition) {
    myStartPosition = startPosition;
  }

  public void setEndLine(int endLine) {
    myEndLine = endLine;
  }

  public void setEndPosition(int endPosition) {
    myEndPosition = endPosition;
  }

  public int getLineDistance() {
    return myEndLine - myStartLine;
  }

  @Override
  public String toString() {
    return myFileName + "(" + myStartLine + ":" + myStartPosition + " " + myEndLine + ":" + myEndPosition + ")";
  }

  @Override
  public int compareTo(@NotNull PositionInfo p) {
    if (myFileName == null) {
      if (p.myFileName != null) {
        return 1;
      }
    } else {
      if (p.myFileName == null) {
        return -1;
      }
      int compareTo = myFileName.compareTo(p.myFileName);
      if (compareTo != 0) {
        return compareTo;
      }
    }
    if (getLineDistance() == p.getLineDistance()) {
      if (myStartLine == p.myStartLine) {
        if (myStartPosition == p.myStartPosition) {
          if (myEndPosition == p.myEndPosition) {
            if (myNodeId == null) {
              if (p.myNodeId == null) {
                return 0;
              }
              return -1;
            }
            if (p.myNodeId == null) {
              return 1;
            }
            return myNodeId.compareTo(p.myNodeId);
          } else {
            return myEndPosition - p.myEndPosition;
          }
        } else {
          return myStartPosition - p.myStartPosition;
        }
      } else {
        return myStartLine - p.myStartLine;
      }
    } else {
      return getLineDistance() - p.getLineDistance();
    }
  }

  public boolean isOccupyTheSameSpace(PositionInfo p) {
    return (myStartLine == p.myStartLine) && (myEndLine == p.myEndLine) && (myStartPosition == p.myStartPosition) && (myEndPosition == p.myEndPosition);
  }

  public boolean contains(String file, int line) {
    return Objects.equals(myFileName, file) && myStartLine <= line && line <= myEndLine;
  }

  public boolean contains(PositionInfo position) {
    if (!(contains(position.getFileName(), position.getStartLine()))) {
      return false;
    }
    if (!(contains(position.getFileName(), position.getEndLine()))) {
      return false;
    }
    if (myStartLine == position.getStartLine()) {
      if (myStartPosition > position.getStartPosition()) {
        return false;
      }
    }
    if (myEndLine == position.getEndLine()) {
      return myEndPosition <= position.getEndPosition();
    }
    return true;
  }

  public void fillFrom(PositionInfo position) {
    myNodeId = position.myNodeId;
    myFileName = position.myFileName;
    myStartLine = position.myStartLine;
    myStartPosition = position.myStartPosition;
    myEndLine = position.myEndLine;
    myEndPosition = position.myEndPosition;
  }

  public static final class StartLineComparator implements Comparator<PositionInfo> {
    @Override
    public int compare(PositionInfo p1, PositionInfo p2) {
      return p1.getStartLine() - p2.getStartLine();
    }
  }
}
