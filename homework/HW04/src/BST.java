import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The Collection given was null. "
                    + "Provide real data.");
        }

        for (T subData : data) {
            add(subData);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data given was null. "
                    + "Provide real data.");
        } else if (root == null) {
            root = new BSTNode<>(data);
            size++;
        } else {
            root = addHelper(data, root);
        }
    }

    /**
     * Recursive helper method for add. Takes in the data to be added and
     * the starting root.
     * @param data The data to be added
     * @param myNode The starting node usually the root
     * @return a BSTNode that was added
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> myNode) {
        if (myNode == null) {
            myNode = new BSTNode<>(data);
            size++;
            return myNode;
        } else {
            if (myNode.getData().compareTo(data) < 0) {
                myNode.setRight(addHelper(data, myNode.getRight()));
            } else if (myNode.getData().compareTo(data) > 0) {
                myNode.setLeft(addHelper(data, myNode.getLeft()));
            }
            return myNode;
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided cannot be"
                    + " null.");
        } else if (root == null) {
            throw new NoSuchElementException("The element was not found in the "
                    + "tree so it couldn't be removed.");
        } else {
            BSTNode<T> dummyNode = new BSTNode<>(null);
            root = removeHelper(data, root, dummyNode);
            size--;
            return dummyNode.getData();
        }
    }

    /**
     * Recursive helper method for remove. Takes in the data to be removed,
     * the starting node, and an empty node.
     * @param data the data to be removed
     * @param currNode the starting node usually the root
     * @param dummyNode an empty node
     * @return a BSTNode that was removed
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> currNode,
                                    BSTNode<T> dummyNode) {
        if (currNode == null) {
            throw new NoSuchElementException("The element was not found in the "
                    + "tree so it couldn't be removed.");
        } else {
            if (currNode.getData().compareTo(data) < 0) {
                currNode.setRight(removeHelper(data, currNode.getRight(),
                        dummyNode));
                return currNode;
            } else if (currNode.getData().compareTo(data) > 0) {
                currNode.setLeft(removeHelper(data, currNode.getLeft(),
                        dummyNode));
                return currNode;
            } else {
                dummyNode.setData(currNode.getData());
                if (currNode.getLeft() == null && currNode.getRight() == null) {
                    return null;
                } else if (currNode.getLeft() != null && currNode.getRight()
                        == null) {
                    return currNode.getLeft();
                } else if (currNode.getLeft() == null && currNode.getRight()
                        != null) {
                    return currNode.getRight();
                } else {
                    BSTNode<T> predDummy = new BSTNode<>(null);
                    currNode.setLeft(predecessor(predDummy,
                            currNode.getLeft()));
                    currNode.setData(predDummy.getData());
                }
                return currNode;
            }
        }
    }

    /**
     * A recursive method only called in removeHelper for finding
     * the predecessor
     * of the removed node.
     * @param dummyNode an empty node
     * @param currNode the starting node (the left child of the removed node)
     * @return a BSTNode that is the predecessor
     */
    private BSTNode<T> predecessor(BSTNode<T> dummyNode, BSTNode<T> currNode) {
        if (currNode.getRight() != null) {
            currNode.setRight(predecessor(dummyNode, currNode.getRight()));
        } else {
            dummyNode.setData(currNode.getData());
            return currNode.getLeft();
        }
        return currNode;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided was null.");
        } else {
            return getHelper(root, data);
        }
    }

    /**
     * A recursive helper for the get method.
     * @param node the starting node (usually the root)
     * @param data the data you are trying to find
     * @return T the data to be retrieved
     */
    private T getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("The data does not exist in the"
                    + "tree.");
        } else if (node.getData().equals(data)) {
            return node.getData();
        } else if (node.getData().compareTo(data) < 0) {
            return getHelper(node.getRight(), data);
        } else {
            return getHelper(node.getLeft(), data);
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data provided was null.");
        } else {
            return containsHelper(root, data);
        }
    }

    /**
     * A recursive helper method for contains.
     * @param node the starting node usually the root
     * @param data the data you are searching for
     * @return Boolean true if the data was found in the tree, false if not
     */
    private boolean containsHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        } else if (node.getData().compareTo(data) == 0) {
            return true;
        } else if (node.getData().compareTo(data) < 0) {
            return containsHelper(node.getRight(), data);
        } else {
            return containsHelper(node.getLeft(), data);
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> myList = new ArrayList<>(size);
        return preorderHelper(root, myList);
    }

    /**
     * A recursive helper for the preorder method.
     * @param currNode The starting node usually the root
     * @param myList a List for adding the elements of the tree
     * @return List the list of all the elements in preorder
     */
    private List<T> preorderHelper(BSTNode<T> currNode, List<T> myList) {
        if (currNode == null) {
            return myList;
        } else {
            myList.add(currNode.getData());
            preorderHelper(currNode.getLeft(), myList);
            preorderHelper(currNode.getRight(), myList);
            return myList;
        }
    }

    @Override
    public List<T> postorder() {
        List<T> myList = new ArrayList<>(size);
        return postorderHelper(root, myList);
    }

    /**
     * A recursive helper for the postorder method.
     * @param currNode The starting node usually the root
     * @param myList a List for adding the elements of the tree
     * @return List the list of all the elements in postorder
     */
    private List<T> postorderHelper(BSTNode<T> currNode, List<T> myList) {
        if (currNode == null) {
            return myList;
        } else {
            postorderHelper(currNode.getLeft(), myList);
            postorderHelper(currNode.getRight(), myList);
            myList.add(currNode.getData());
        }
        return myList;
    }

    @Override
    public List<T> inorder() {
        List<T> myList = new ArrayList<>(size);
        return inorderHelper(root, myList);
    }

    /**
     * A recursive helper for the inorder method.
     * @param currNode The starting node usually the root
     * @param myList a List for adding the elements of the tree
     * @return List the list of all the elements in inorder
     */
    private List<T> inorderHelper(BSTNode<T> currNode, List<T> myList) {
        if (currNode == null) {
            return myList;
        } else {
            inorderHelper(currNode.getLeft(), myList);
            myList.add(currNode.getData());
            inorderHelper(currNode.getRight(), myList);
            return myList;
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> myList = new ArrayList<>(size);
        Queue<BSTNode<T>> theQ = new LinkedList<>();
        theQ.add(root);
        while (theQ.size() != 0) {
            if (theQ.peek() != null) {
                theQ.add(theQ.peek().getLeft());
                theQ.add(theQ.peek().getRight());
                myList.add(theQ.remove().getData());
            } else {
                theQ.remove();
            }
        }
        return myList;
    }

    @Override
    public List<T> listLeavesDescending() {
        List<T> myList = new ArrayList<>(size);
        return listLeavesDescendingHelper(root, myList);
    }

    /**
     * A recursive helper for the listLeavesDescending method.
     * @param currNode The starting node usually the root
     * @param myList a List for adding the leaves of the tree
     * @return List the list of all the leaves in descending order
     */
    private List<T> listLeavesDescendingHelper(BSTNode<T> currNode,
                                               List<T> myList) {
        if (currNode == null) {
            return myList;
        } else {
            if (currNode.getLeft() == null && currNode.getRight() == null) {
                myList.add(currNode.getData());
            } else {
                listLeavesDescendingHelper(currNode.getRight(), myList);
                listLeavesDescendingHelper(currNode.getLeft(), myList);
            }
            return myList;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return heightHelper(root, 0);
        }
    }

    /**
     * A recursive helper for the height method.
     * @param currNode the starting node usually the root
     * @param i the int value of the height
     * @return int height of the tree
     */
    private int heightHelper(BSTNode<T> currNode, int i) {
        if (currNode == null) {
            return -1;
        } else {
            if (currNode.getLeft() == null && currNode.getRight() == null) {
                return 0;
            } else {
                return 1 + Math.max(heightHelper(currNode.getLeft(), i),
                        heightHelper(currNode.getRight(), i));
            }
        }

    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
