package org.hswebframework.web.organizational.authorization.relation;

import java.io.Serializable;

/**
 * 关系，用于获�?�人员等关系信�?�
 *
 * @author zhouhao
 * @see Relations
 * @since 3.0
 */
public interface Relation extends Serializable {

    /**
     * 默认类型:机构
     */
    String TYPE_ORG = "org";

    /**
     * 默认类型:部门
     */
    String TYPE_DEPARTMENT = "department";

    /**
     * 默认类型:岗�?
     */
    String TYPE_POSITION = "position";

    /**
     * 默认类型:人员
     */
    String TYPE_PERSON = "person";

    /**
     * @return 关系维度，如:person,department
     */
    String getDimension();

    /**
     * @return 关系，如: leader,member
     */
    String getRelation();

    /**
     * @return 关系目标表识（和�?建立关系），通常为目标的id
     */
    String getTarget();

    /**
     * @return 关系目标对象，用于获�?�建立关系对象完整信�?�，返回值的类型�?�能�?�?�{@link this#getDimension()}的�?�?�而�?�化
     * @see RelationTargetSupplier
     */
    Serializable getTargetObject();

    /**
     * @return 关系�??称，与{@link this#getDimension()} 对应，如: �?�?�,员工
     */
    String getName();

    /**
     * @return 关系的方�?�
     * @see Direction
     */
    Direction getDirection();

    /**
     * 匹�?方�?�，如果当�?的方�?�为ALl，则全部返回true
     * <pre>
     *     direction=ALL;
     *     matchDirection(POSITIVE) -> true
     *     matchDirection(REVERSE) -> true
     *     matchDirection(ALL) -> true
     * </pre>
     * <p>
     * <pre>
     *     direction=POSITIVE;
     *     matchDirection(POSITIVE) -> true
     *     matchDirection(REVERSE) -> false
     *     matchDirection(ALL) -> false
     * </pre>
     *
     * @param direction �?匹�?的方�?�枚举
     * @return 匹�?结果
     */
    default boolean matchDirection(Direction direction) {
        return getDirection() == Direction.ALL || getDirection() == direction;
    }

    /**
     * 匹�?方�?�，如果当�?的方�?�为ALl，则全部返回true
     * <pre>
     *     direction=ALL;
     *     matchDirection("A") -> true
     *     matchDirection("ALL") -> true
     *     matchDirection("R") -> true
     *     matchDirection("P") -> true
     *     matchDirection("O") -> false
     * </pre>
     * <p>
     * <pre>
     *     direction=POSITIVE;
     *     matchDirection("P") -> true
     *     matchDirection("POS") -> true
     *     matchDirection("A") -> false
     *     matchDirection("O") -> false
     * </pre>
     *
     * @param direction �?匹�?的方�?�字符
     * @return 匹�?结果
     * @see Direction#fromString(String)
     */
    default boolean matchDirection(String direction) {
        return matchDirection(Direction.fromString(direction));
    }

    /**
     * 关系方�?�,例如，我和张三建立关系，POSITIVE：我是张三的�?�?� ，REVERSE张三是我的�?�?�
     *
     * @author zhouhao
     * @since 3.0
     */
    enum Direction {
        /**
         * 正�?�关系
         */
        POSITIVE,
        /**
         * �??�?�关系
         */
        REVERSE,
        /**
         * �?��?�关系
         */
        ALL;

        public static Direction fromString(String direction) {
            if (direction == null) {
                return null;
            }
            for (Direction dir : values()) {
                //以�??称开头则认为是�?�一个方�?�
                if (dir.name().startsWith(direction.toUpperCase())) {
                    return dir;
                }
            }
            return null;
        }
    }

}
