import org.w3c.dom.Node;

public class BinaryTree<T extends Comparable<T>>
{
    class Node
    {
        private Node parent = null;
        final private T value;
        private Node left = null;
        private Node right = null;

        Node(T element)
        {
            this.value = element;
        }

        Node(T element, Node parent)
        {
            this.value = element;
            this.parent = parent;
        }
    }

    private Node root = null;
    
    public BinaryTree()
    {

    }

    public boolean search(T wantedElement)
    {
        if (findNode(wantedElement) == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void insert(T element)
    {
        root = insertRec(element, root, null);
    }

    private Node insertRec(T element, Node currentNode, Node currentNodeParent)
    {
        if (currentNode == null)
        {
            if (currentNodeParent != null)
            {
                // System.out.println(element + ": Mój parent to: " + currentNodeParent.value);
            }
            return new Node(element, currentNodeParent);
        }

        if (element.compareTo(currentNode.value) > 0)
        {
            currentNode.right = insertRec(element, currentNode.right, currentNode);
        }
        else if (element.compareTo(currentNode.value) < 0)
        {
            currentNode.left = insertRec(element, currentNode.left, currentNode);
        }
        
        return currentNode;
    }

    private Node findNode(T element)
    {
        Node currentNode = root;

        while (currentNode != null)
        {
            if (element.compareTo(currentNode.value) == 0)
            {
                return currentNode;
            }

            if (element.compareTo(currentNode.value) < 0)
            {
                currentNode = currentNode.left;
            }
            else if (element.compareTo(currentNode.value) > 0)
            {
                currentNode = currentNode.right;
            }
        }

        return null;
    }

    public boolean delete(T element)
    {
        Node node = findNode(element);

        if (node == null)
        {
            return false;
        }

        deleteRec(node);
        return true;
    }

    private Node deleteRec(Node node)
    {
        Node nodeParent = node.parent;
        Node nodeHelp;

        if (node.left != null && node.right != null)
        {
            nodeHelp = deleteRec(getSuccessor(node));
            nodeHelp.left = node.left;

            if (nodeHelp.left != null)
            {
                nodeHelp.left.parent = nodeHelp;
            }

            nodeHelp.right = node.right;
            if (nodeHelp.right != null)
            {
                nodeHelp.right.parent = nodeHelp;
            }
        }
        else
        {
            if (node.left != null)
            {
                nodeHelp = node.left;
            }
            else
            {
                nodeHelp = node.right;
            }
        }

        if (nodeHelp != null)
        {
            nodeHelp.parent = nodeParent;
        }

        if (nodeParent == null)
        {
            root = nodeHelp;
        }
        else if (nodeParent.left == node)
        {
            nodeParent.left = nodeHelp;
        }
        else
        {
            nodeParent.right = nodeHelp;
        }

        return node;  
    }

    private Node getPredecessor(Node node)
    {
        if (node.left != null)
        {
            return getPredecessor(node.left);
        }

        Node helpNode;

        do
        {
            helpNode = node;
            node = node.parent;
        } while (node != null && node.right != helpNode);

        return node;
    }

    private Node getSuccessor(Node node)
    {
        if (node.right != null)
        {
            return minNode(node.right);
        }

        Node helpNode;

        do
        {
            helpNode = node;
            node = node.parent;
        } while (node != null && node.left != helpNode);

        return node;
    }

    private Node minNode(Node node)
    {
        while (node.left != null)
        {
            node = node.left;
        }

        return node;
    }

    public void draw()
    {
        drawRec(root);
    }

    private void drawRec(Node currentNode)
    {
        if (currentNode != null)
        {
            drawRec(currentNode.left);

            System.out.println(currentNode.value);

            drawRec(currentNode.right);
        }
    }
}
