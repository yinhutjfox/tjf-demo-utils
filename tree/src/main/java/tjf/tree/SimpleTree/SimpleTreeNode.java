package tjf.tree.SimpleTree;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * TODO: 删除节点，删除节点更新节点数，记录每个节点下的节点数，实现一个遍历迭代器
 * @ClassName: SimpleTreeNode
 * @Description:
 * @Author: yinhutjfox
 * @Date: 2019/4/3 22:21
 */
public class SimpleTreeNode<T> implements Serializable {

    private static final long serialVersionUID = 6626046460510162913L;

    private T self = null;

    private SimpleTreeNode<T> parent = null;

    private List<SimpleTreeNode<T>> children = null;

    private volatile static int size = 1;

    private final static Object SIZE_LOCK = new Object();

    public SimpleTreeNode(){}

    public SimpleTreeNode(T self) {
        this.self = self;
    }

    public SimpleTreeNode(T self , SimpleTreeNode<T> parent) {
        this.self = self;
        this.parent = parent;
    }

    public void addChild(SimpleTreeNode<T> child) {
        if(null != child) {
            if(null == children) {
                children = new ArrayList<>();
            }
            children.add(child);
            synchronized (SIZE_LOCK) {
                ++size;
            }
        }
    }

    public T getSelf() {
        return self;
    }

    public void setSelf(T self) {
        this.self = self;
    }

    public SimpleTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(SimpleTreeNode<T> parent) {
        this.parent = parent;
    }

    public List<SimpleTreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleTreeNode<T>> children) {
        this.children = children;
    }

    public int size() {
        return size;
    }

    public boolean isRoot() {
        return null == parent;
    }

    public boolean isLeaf() {
        return null == children || 0 == children.size();
    }
}
